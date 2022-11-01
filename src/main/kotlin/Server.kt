import java.io.BufferedReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread


class Server() {
    private lateinit var socket: Socket
    private lateinit var server: ServerSocket

    fun startServer(port: String?, name: String?) {
        try {
            server = ServerSocket(port!!.toInt())
            println("The Server is accepting requests")
            println("Waiting...")
            socket = server.accept()
            Graphical(name, socket)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}