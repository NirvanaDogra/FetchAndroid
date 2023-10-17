package com.example.fetchassesment.model

import org.junit.Test
import org.junit.Assert.assertEquals

internal class HiringApiServiceDataModelTest {
    @Test
    fun `when HiringApiServiceDataModel is initialized then validate the values`() {
        // given
        val model = HiringApiServiceDataModel(1, "2", "name")
        // when
        // then
        assertEquals(1, model.id)
        assertEquals("2", model.listId)
        assertEquals("name", model.name)
    }
}