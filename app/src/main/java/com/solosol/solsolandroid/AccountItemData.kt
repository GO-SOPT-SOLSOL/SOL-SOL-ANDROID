package com.solosol.solsolandroid

data class AccountItemData(
    val accountNumber:String,
    val bank: String,
    val money:String,
    val lastPostList: List<PostItem>
    ) {
    data class PostItem(
        val bank: String,
        val name: String,
        val money: String
    )
}


