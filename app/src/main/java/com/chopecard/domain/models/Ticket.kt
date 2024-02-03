package com.chopecard.domain.models

data class Ticket(val ticketId: Int, val subject: String, val messages: List<TicketMessage>)
