package me.kartdroid.androidkitchen2.orders.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class PreviewPickupDropProvider : PreviewParameterProvider<PreviewPickupDropProvider.PreviewPickupDrop> {
    override val values = sequenceOf(
        PreviewPickupDrop(
            pickupTitle = "Sector 3, HSR Layout",
            dropTitle = "Sector 7, HSR Layout",
            pickupAddress = "#562, 14th main road,\nSector 3, HSR Layout",
            dropAddress = "#562, 14th main road,\nSector 7, HSR Layout",
            batchOrderSize = 2
        ),
        PreviewPickupDrop(
            pickupTitle = "Sector 3, HSR Layout",
            dropTitle = "Sector 7, HSR Layout",
            pickupAddress = "",
            dropAddress = "#562, 14th main road,\nSector 7, HSR Layout",
            batchOrderSize = 2
        ),
        PreviewPickupDrop(
            pickupTitle = "Sector 3, HSR Layout",
            dropTitle = "Sector 7, HSR Layout",
            pickupAddress = "#562, 14th main road,\nSector 3, HSR Layout",
            dropAddress = "#562, 14th main road,\nSector 7, HSR Layout",
            batchOrderSize = 0
        ),
        PreviewPickupDrop(
            pickupTitle = "Sector 3, HSR Layout",
            dropTitle = "Sector 7, HSR Layout",
            pickupAddress = "#562, 14th main road,\nSector 3, HSR Layout",
            dropAddress = "",
            batchOrderSize = 0
        ),
        PreviewPickupDrop(
            pickupTitle = "Sector 3, HSR Layout",
            dropTitle = "",
            pickupAddress = "#562, 14th main road,\nSector 3, HSR Layout",
            dropAddress = "",
            batchOrderSize = 2
        ),
        PreviewPickupDrop(
            pickupTitle = "Sector 3, HSR Layout",
            dropTitle = "",
            pickupAddress = "#562, 14th main road,\nSector 3, HSR Layout",
            dropAddress = "",
            batchOrderSize = 0
        ),
        PreviewPickupDrop(
            pickupTitle = "Sector 3, HSR Layout",
            dropTitle = "",
            pickupAddress = "",
            dropAddress = "",
            batchOrderSize = 2
        ),
        PreviewPickupDrop(
            pickupTitle = "",
            dropTitle = "",
            pickupAddress = "#562, 14th main road,\nSector 3, HSR Layout",
            dropAddress = "#562, 14th main road,\nSector 7, HSR Layout",
            batchOrderSize = 0
        )
    )

    data class PreviewPickupDrop(
        val pickupTitle: String = "",
        val dropTitle: String = "",
        val pickupAddress: String = "",
        val dropAddress: String = "",
        val batchOrderSize: Int = 0
    )
}
