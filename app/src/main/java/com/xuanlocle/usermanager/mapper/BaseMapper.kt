package com.xuanlocle.usermanager.mapper

interface BaseMapper<E, D> {

    fun transformToDomain(type: E): D

    fun transformToDTO(type: D): E
}