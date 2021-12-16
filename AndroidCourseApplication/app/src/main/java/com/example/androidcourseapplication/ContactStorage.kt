package com.example.androidcourseapplication

object ContactStorage {
    fun getContactList(): List<Contact> {
        return listOf(
            Contact(
                id = 1,
                photo = R.drawable.patric,
                name = "Иван",
                phoneNums = listOf("88005553535", "4242"),
                emails = listOf("ivan@mail.ru", "blazerboy69@mail.ru"),
                description = "ровный чел"
            )
        )
    }
}