package com.example.fetchassesment.model

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

internal class SectionHeadingDataModelTest {
    @Test
    fun `when SectionHeadingDataModel is initialized then validate the values`() {
        // given
        val model = SectionHeadingDataModel("name")
        // when
        // then
        assertEquals("name", model.heading)
    }
}