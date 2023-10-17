package com.example.fetchassesment.model

import org.junit.Assert.assertEquals
import org.junit.Test

internal class SectionListDataModelTest{
    @Test
    fun `when SectionListDataModel is initialized then validate the values`() {
        // given
        val model = SectionListDataModel(1, "2", "name")
        // when
        // then
        assertEquals(1, model.id)
        assertEquals("2", model.listId)
        assertEquals("name", model.name)
    }
}