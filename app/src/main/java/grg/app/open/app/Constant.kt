package grg.app.open.app

import grg.app.open.app.component.EventId


const val KEY_ARTICLE_URL = "KEY_ARTICLE_URL"


//event action id
val ACTION_LOGIN = EventId(0x00, Integer::class.java)

val ACTION_LOGOUT = EventId(0x01, String::class.java)