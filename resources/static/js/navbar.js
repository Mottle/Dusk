const topBar = $('#top-bar')
// topBar.css('opacity', 0)
const topBarMaxOpacity = 0.8
const topBarHeight = topBar.css("height")
let lockTopBarShown = false

$(window).scroll(() => {
    const offset = parseInt($(window).scrollTop())
    console.log("scroll " + offset.toString())
    if (offset >= topBarHeight) {
        if (!lockTopBarShown) {
            lockTopBarShown = true
        }
    } else {
        lockTopBarShown = false
    }
})