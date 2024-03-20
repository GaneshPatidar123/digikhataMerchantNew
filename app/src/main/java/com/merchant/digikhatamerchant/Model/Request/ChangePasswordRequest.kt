package com.merchant.digikhatamerchant.Model.Request

data class ChangePasswordRequest(
    var BusinessId :String?,
    var DeviceCode :String?,
    var DevicePassword :String?,
    var NewPassword :String?,
)
