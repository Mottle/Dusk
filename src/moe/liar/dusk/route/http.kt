@file:Suppress("unused")

package moe.liar.dusk.route

enum class HttpMethod {
    GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH
}

enum class HttpStatusCode(val code: Int, val info: String) {
    Continue(100, "Continue"),
    SwitchingProtocol(101, "Switching Protocol"),
    EarlyHints(103, "Early Hints"),

    Ok(200, "Ok"),
    Created(201, "Created"),
    Accepted(202, "Accepted"),
    NonAuthoritativeInformation(203, "Non-Authoritative Information"),
    NoContent(204, "No Content"),
    ResetContent(205, "Reset Content"),
    PartialContent(206, "Partial Content"),

    MultipleChoice(300, "Multiple Choice"),
    MovedPermanently(301, "Moved Permanently"),
    Found(302, "Found"),
    SeeOther(303, "See Other"),
    NotModified(304, "Not Modified"),
    TemporaryRedirect(307, "Temporary Redirect"),
    PermanentRedirect(308, "Permanent Redirect"),

    BadRequest(400, "Bad Request"),
    Unauthorized(401, "Unauthorized"),
    Forbidden(403, "Forbidden"),
    NotFound(404, "Not Found"),
    MethodNotAllowed(405, "Method Not Allowed"),
    NotAcceptable(406, "Not Acceptable"),
    ProxyAuthenticationRequired(407, "Proxy Authentication Required"),
    RequestTimeout(408, "Request Timeout"),
    Conflict(409, "Conflict"),
    Gone(410, "Gone"),
    LengthRequired(411, "Length Required"),
    PreconditionFailed(412, "Precondition Failed"),
    PayloadTooLarge(413, "Payload Too Large"),
    URITooLong(414, "URI Too Long"),
    UnsupportedMediaType(415, "Unsupported Media Type"),
    RangeNotSatisfiable(416, "Range Not Satisfiable"),
    ExpectationFailed(417, "Expectation Failed"),
    IAmATeapot(418, "I'm a teapot"),
    TooEarly(425, "Too Early"),
    UpgradeRequired(426, "Upgrade Required"),
    PreconditionRequired(428, "Precondition Required"),
    TooManyRequests(429, "Too Many Requests"),
    RequestHeaderFieldsTooLarge(431, "Request Header Fields Too Large"),
    UnavailableForLegalReasons(451, "Unavailable For Legal Reasons"),

    InternalServerError(500, "Internal Server Error"),
    NotImplemented(501, "Not Implemented"),
    BadGateway(502, "Bad Gateway"),
    ServiceUnavailable(503, "Service Unavailable"),
    GatewayTimeout(504, "Gateway Timeout"),
    HTTPVersionNotSupported(505, "HTTP Version Not Supported"),
    VariantAlsoNegotiates(506, " Variant Also Negotiates"),
    InsufficientStorage(507, "Insufficient Storage"),
    NotExtended(510, "Not Extended"),
    NetworkAuthenticationRequired(511, "Network Authentication Required"),
}

enum class HttpContentType(val value: String) {
    Html("text/html"),
    Plain("text/plain"),
    Xml("text/xml"),
    Gif("image/gif"),
    Jpeg("image/jpeg"),
    Png("image/png"),
    Json("application/json"),
    MultiPartForm("multipart/form-data"),
    Form("application/x-www-form-urlencoded")
}