package com.ldbrossoftwares.digitalpaisa

class Constants {

    companion object {

        private const val PROTOCOL: String = "https://"
        private const val DOMAIN_NAME: String = "api.coincap.io/"

        //Base URL
        const val BASE_URL: String = "$PROTOCOL$DOMAIN_NAME"

        //URL PATH
        const val V2: String = "v2/"
        const val ASSETS: String = "assets"

        //DECIMAL POINT
        const val TWO_DECIMAL_POINT: String = "%.2f"
        const val SIX_DECIMAL_POINT: String = "%.6f"

        //Strings
        const val EMPTY_STRING: String = ""
        const val PERCENT: String = "%"
        const val DOLLAR: String = "$"

        //Numerals
        const val ONE: Int  = 1

    }

}