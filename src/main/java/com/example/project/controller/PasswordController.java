package com.example.project.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.entity.user;
import com.example.project.service.EmailService;
import com.example.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	private Optional<user> optional;

	private Optional<user> user;
	
	public static final String UPLOAD_DIR = "upload";

	
    

	// Display form to reset password
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

		user = userService.findByResetToken(token);

		if (user.isPresent()) { // Token found in DB
			modelAndView.addObject("resetToken", token);

		} else { // Token not found in DB
			modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
		}

		modelAndView.setViewName("resetPassword");
		return modelAndView;
	}

	// Process reset password form
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam(value = "token") String token,
			@RequestParam(value = "password") String password, RedirectAttributes redir) {

		// Find the user associated with the reset token
		user = userService.findByResetToken(token);

		// This should always be non-null but we check just in case
		if (user.isPresent()) {

			user resetUser = user.get();

			// Set new password
			resetUser.setPassword(password);

			// Set the reset token to null so it cannot be used again
			resetUser.setResetToken(null);

			// Save user
			userService.save(resetUser);

			// In order to set a model attribute on a redirect, we must use
			// RedirectAttributes
			redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

			modelAndView.setViewName("redirect:login");
			return modelAndView;

		} else {
			modelAndView.addObject("errorMessage", "This is an invalid password reset link.");
			modelAndView.setViewName("resetPassword");
		}

		return modelAndView;
	}

	// Process form submission from forgotPassword page
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail,
			HttpServletRequest request) {

		// Lookup user in database by e-mail
		optional = userService.findByEmail(userEmail);

		if (!optional.isPresent()) {
			modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
		} else {

			// Generate random 36-character string token for reset password
			user user = optional.get();
			((com.example.project.entity.user) user).setResetToken(UUID.randomUUID().toString());

			// Save token to database
			userService.save((com.example.project.entity.user) user);

			String appUrl = request.getScheme() + "://" + request.getServerName();

			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("taixexedo@gmail.com");
			passwordResetEmail.setTo(((com.example.project.entity.user) user).getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
					+ ":8080/reset?token=" + ((com.example.project.entity.user) user).getResetToken());

			emailService.sendMail(passwordResetEmail);

			// Add success message to view
			modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
		}

		modelAndView.setViewName("forgot-password");
		return modelAndView;

	}

	// Display forgotPassword page
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ModelAndView displayForgotPasswordPage() {
		return new ModelAndView("forgot-password");
	}

	@RequestMapping(value = "/changeUserPass", method = RequestMethod.POST)
	public String changeUserPass(@RequestParam("oldPass") String password, @RequestParam("newPass") String newPassword,
			@RequestParam("reNewPass") String confirmPassword, RedirectAttributes redir, HttpSession session) {
		user user = (user) session.getAttribute("user");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordCorrect = passwordEncoder.matches(password,user.getPassword());
		if (!newPassword.equals(confirmPassword)) {
			redir.addFlashAttribute("errorMessage", "Your new password and confirm password are not match.");
			return "redirect:/user-profile";
		}
		if (!isPasswordCorrect) {
			redir.addFlashAttribute("errorMessage", "Your old password is not correct.");
			return "redirect:/user-profile";
		}
		if (isPasswordCorrect && newPassword.equalsIgnoreCase(confirmPassword)) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userService.saveImage(user);
			redir.addFlashAttribute("successMessage", "Change password successfully.");
			return "redirect:/user-profile";
		}

		else {
			redir.addFlashAttribute("errorMessage", "Your password is not correct.");
			return "redirect:/user-profile";
		}
	}

	
	//
}