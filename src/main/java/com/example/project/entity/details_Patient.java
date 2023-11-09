package com.example.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class details_Patient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int detailsPatientId;
  // set auto tang
  @ManyToOne
  @JoinColumn(nullable = false, name = "patient_id")
  private patient patient;

  // set trong database UQ
  private int reservation_id;
  private String Family_medical_history;
  private String medical_history;
  @Column(nullable = false)
  private LocalDateTime create_at;
  @Column(nullable = false)
  private String create_by;
  // session full_name

  @Column(nullable = false)
  private int heartbeat;
  // bpm
  @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
  private int body_temperature;
  @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Unknown'")
  private String Blood;
  @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Unknown'")
  private String height;
  // cm
  @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Unknown'")
  private String weight;
  // kg

  @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Unknown'")
  private String BMI;
  // chỉ số khối cơ thể
  @Column(nullable = true, columnDefinition = "double DEFAULT 0")
  private double Hemoglobin;
  // g/dL huyết sắc tố, viết tắt Hb hay Hgb
  @Column(nullable = true, columnDefinition = "Double DEFAULT 10")
  private double lefteye;
  @Column(nullable = true, columnDefinition = "Double DEFAULT 10")
  private double righteye;
  @Column(nullable = true, columnDefinition = "VARCHAR(255) DEFAULT 'Unknown'")
  private String IOP;
  // mmhg nhãn áp
  @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Normal'")
  private String description;
  @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Normal'")
  private String heal_description;
  // based of on bmi
  @Column(nullable = true, columnDefinition = "VARCHAR(255) DEFAULT 'Normal'")
  private String eyes_description;
  @Column(nullable = true, columnDefinition = "VARCHAR(255) DEFAULT 'Normal'")
  private String lefteye_description;
  @Column(nullable = true, columnDefinition = "VARCHAR(255) DEFAULT 'Normal'")
  private String righteye_description;

  private int doctor_id;

  public details_Patient() {
  }

  public details_Patient(patient patient, LocalDateTime create_at, String create_by,
      String family_medical_history, String medical_history, int heartbeat, int body_temperature, String blood,
      String height, String weight, String bMI, double hemoglobin, double lefteye, double righteye, String iOP,
      String description, String heal_description, String eyes_description, String lefteye_description,
      String righteye_description) {
    this.patient = patient;
    this.create_at = create_at;
    this.create_by = create_by;
    Family_medical_history = family_medical_history;
    this.medical_history = medical_history;
    this.heartbeat = heartbeat;
    this.body_temperature = body_temperature;
    Blood = blood;
    this.height = height;
    this.weight = weight;
    BMI = bMI;
    Hemoglobin = hemoglobin;
    this.lefteye = lefteye;
    this.righteye = righteye;
    IOP = iOP;
    this.description = description;
    this.heal_description = heal_description;
    this.eyes_description = eyes_description;
    this.lefteye_description = lefteye_description;
    this.righteye_description = righteye_description;
  }

  public int getDetailsPatientId() {
    return detailsPatientId;
  }

  public void setDetailsPatientId(int detailsPatientId) {
    this.detailsPatientId = detailsPatientId;
  }

  public patient getPatient() {
    return patient;
  }

  public void setPatient(patient patient) {
    this.patient = patient;
  }

  public LocalDateTime getCreate_at() {
    return create_at;
  }

  public void setCreate_at(LocalDateTime create_at) {
    this.create_at = create_at;
  }

  public String getCreate_by() {
    return create_by;
  }

  public void setCreate_by(String create_by) {
    this.create_by = create_by;
  }

  public String getFamily_medical_history() {
    return Family_medical_history;
  }

  public void setFamily_medical_history(String family_medical_history) {
    Family_medical_history = family_medical_history;
  }

  public String getMedical_history() {
    return medical_history;
  }

  public void setMedical_history(String medical_history) {
    this.medical_history = medical_history;
  }

  public int getHeartbeat() {
    return heartbeat;
  }

  public void setHeartbeat(int heartbeat) {
    this.heartbeat = heartbeat;
  }

  public int getBody_temperature() {
    return body_temperature;
  }

  public void setBody_temperature(int body_temperature) {
    this.body_temperature = body_temperature;
  }

  public String getBlood() {
    return Blood;
  }

  public void setBlood(String blood) {
    Blood = blood;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getBMI() {
    return BMI;
  }

  public void setBMI(String bMI) {
    BMI = bMI;
  }

  public double getHemoglobin() {
    return Hemoglobin;
  }

  public void setHemoglobin(double hemoglobin) {
    Hemoglobin = hemoglobin;
  }

  public double getLefteye() {
    return lefteye;
  }

  public void setLefteye(double lefteye) {
    this.lefteye = lefteye;
  }

  public double getRighteye() {
    return righteye;
  }

  public void setRighteye(double righteye) {
    this.righteye = righteye;
  }

  public String getIOP() {
    return IOP;
  }

  public void setIOP(String iOP) {
    IOP = iOP;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHeal_description() {
    return heal_description;
  }

  public void setHeal_description(String heal_description) {
    this.heal_description = heal_description;
  }

  public String getEyes_description() {
    return eyes_description;
  }

  public void setEyes_description(String eyes_description) {
    this.eyes_description = eyes_description;
  }

  public String getLefteye_description() {
    return lefteye_description;
  }

  public void setLefteye_description(String lefteye_description) {
    this.lefteye_description = lefteye_description;
  }

  public String getRighteye_description() {
    return righteye_description;
  }

  public void setRighteye_description(String righteye_description) {
    this.righteye_description = righteye_description;
  }

  public int getDoctor_id() {
    return doctor_id;
  }

  public void setDoctor_id(int doctor_id) {
    this.doctor_id = doctor_id;
  }

  public int getReservation_id() {
    return reservation_id;
  }

  public void setReservation_id(int reservation_id) {
    this.reservation_id = reservation_id;
  }

}
