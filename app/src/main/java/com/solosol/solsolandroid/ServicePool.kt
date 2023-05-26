package com.solosol.solsolandroid

object ServicePool {
    val solService = ApiFactory.create<SolService>()
}