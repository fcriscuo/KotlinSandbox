package web

import java.awt.Desktop.getDesktop
import java.io.IOException
import java.net.URI


object RunningDefaultBrowser {
    @JvmStatic
    fun main(args: Array<String>) {
        val uri: URI = URI.create("https://commons.apache.org/")
        try {
            // Get Desktop instance of the current browser context. An
            // UnsupportedOperationException will be thrown if the
            // platform doesn't support Desktop API.
            val desktop = getDesktop()

            // Browse the uri using user's default web browser.
            desktop.browse(uri)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}