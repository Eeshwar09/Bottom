package com.example.Bottom

class Detailss {
    var name: String = ""
    var email: String = ""
    var school: String = ""
    var grade: String = ""
    var college: String = ""
    var percentage: String = ""
    var university: String = ""
    var percentage1: String = ""
    var address: String = ""
    var hobbies: String = ""
    var contact_no: String = ""


    constructor() {}

    constructor(
        name: String, email: String, school: String,
        grade: String, college: String, percentage: String,
        university: String, percentage1: String, address: String,
        hobbies: String, contact_no: String
    ) {
        this.name = name
        this.email = email
        this.school = school
        this.grade = grade
        this.college = college
        this.percentage = percentage
        this.university = university
        this.percentage1 = percentage1
        this.address = address
        this.hobbies = hobbies
        this.contact_no = contact_no
    }
}