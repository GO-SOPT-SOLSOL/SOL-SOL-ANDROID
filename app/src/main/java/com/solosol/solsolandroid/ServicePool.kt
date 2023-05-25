package com.solosol.solsolandroid

import com.solosol.solsolandroid.transfer3.ApiFactory

object ServicePool {
    val solService = ApiFactory.create<SolService>()
}