package com.shrek.im_client.net

object ConnectionManager {

    private val connections: HashMap<ConnectionType, IConnection> = hashMapOf()

    private fun createMessageConnection(): IConnection {
        if (connections[ConnectionType.Message] == null) {
            connections[ConnectionType.Message] = Connection()
        }
        return connections[ConnectionType.Message] ?: throw IllegalStateException("Message Connection not found")
    }

    suspend fun startMessageConnection(address: String = Configuration.SERVER_ADDRESS, port: Int = Configuration.SERVER_PORT) {
        createMessageConnection().connected(address, port)
    }

    suspend fun closeMessageConnection() {
        createMessageConnection().close()
        connections.remove(ConnectionType.Message)
    }
}