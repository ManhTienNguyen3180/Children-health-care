package com.example.project.entity;

import jakarta.persistence.*;

@Entity
public class contact {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int contactId;
  @Column(nullable = false)
  private String province;
  @Column(nullable = false)
  private String district;
  @Column(nullable = false)
  private String ward;
  @OneToOne
  @JoinColumn(nullable = false, name = "patient_id")
  private patient patient;

  public contact() {
  }

  public contact(String province, String district, String ward, patient patient) {
    this.province = province;
    this.district = district;
    this.ward = ward;
    this.patient = patient;
  }

  public int getContactId() {
    return contactId;
  }

  public void setContactId(int contactId) {
    this.contactId = contactId;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getWard() {
    return ward;
  }

  public void setWard(String ward) {
    this.ward = ward;
  }

  public patient getPatient() {
    return patient;
  }

  public void setPatient(patient patient) {
    this.patient = patient;
  }

}
