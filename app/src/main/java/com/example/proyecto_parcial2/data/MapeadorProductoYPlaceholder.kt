package com.example.proyecto_parcial2.data

import com.example.proyecto_parcial2.data.remote.PostDto
import com.example.proyecto_parcial2.data.remote.ProductDto
import com.example.proyecto_parcial2.models.Cosas
import com.example.proyecto_parcial2.models.Placeholder

object MapeadorProductoYPlaceholder {

    // 1. Mapeo para Product (ProductDto -> Cosas)
    fun mapProductDtoToCosas(dto: ProductDto): Cosas {
        return Cosas(
            titulo = dto.title ?: "",
            descripcion = dto.description ?: "",
            categoria = dto.category ?: "",
            precio = dto.price ?: 0.0,
            porcentajeDescuento = dto.discountPercentage ?: 0.0,
            thumbnail = dto.thumbnail ?: ""
        )
    }

    fun mapProductDtoListToCosasList(dtos: List<ProductDto>?): List<Cosas> {
        return dtos?.map { mapProductDtoToCosas(it) } ?: emptyList()
    }

    // 2. Mapeo para JSONPlaceholder (PostDto -> Placeholder)
    fun mapPostDtoToPlaceholder(dto: PostDto): Placeholder {
        return Placeholder(
            title = dto.title ?: "",
            body = dto.body ?: ""
        )
    }

    fun mapPostDtoListToPlaceholderList(dtos: List<PostDto>?): List<Placeholder> {
        return dtos?.map { mapPostDtoToPlaceholder(it) } ?: emptyList()
    }
}
