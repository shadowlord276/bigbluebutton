package org.bigbluebutton.apps.protocol

import spray.json.JsValue
import org.bigbluebutton.apps.models.User
import spray.json.DefaultJsonProtocol
import spray.httpx.SprayJsonSupport

case class Header(event: HeaderEvent, meeting: HeaderMeeting)
case class HeaderEvent(name: String, timestamp: Long, 
                       correlation: String, source: String)
                       
case class HeaderMeeting(name: String, externalId: String, sessionId: Option[String] = None)
case class HeaderAndPayload(header: Header, payload: JsValue)

case class MessageProcessException(message: String) extends Exception(message)


object InMessageNameContants {
  val CreateMeetingRequestMessage = "CreateMeetingRequest"
  val RegisterUserRequestMessage = "RegisterUserRequest"
}


case class RegisterUserRequest(header: Header, payload: User) extends InMessage
case class AssignPresenter(header: Header, payload: String)



case class Ok(id: Int)

object HeaderAndPayloadJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {  
  implicit val header8EventFormat = jsonFormat4(HeaderEvent)
  implicit val header8MeetingFormat = jsonFormat3(HeaderMeeting)
  implicit val header8Format = jsonFormat2(Header)  
  implicit val header8AndPayloadFormat = jsonFormat2(HeaderAndPayload)
}