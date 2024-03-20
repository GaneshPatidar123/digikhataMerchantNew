package com.example.authdemo.models.response.auth



data class SignUpResponse(
    var BS : Any,
    var ErrDesc: String="",
    var Status: String?,
    var Msg :String?,
    var ErrorDes :String?,
    var description :String?,
    var result :String?,
    var RowsCount : String?
)
