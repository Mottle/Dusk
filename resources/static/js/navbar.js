const topBar = $('#top-bar')
// topBar.css('opacity', 0)
const topBarMaxOpacity = 0.8
const topBarHeight = 70
let lockTopBarShown = false

$(window).scroll(() => {
    const offset = parseInt($(window).scrollTop())
    // console.log('scroll ' + offset.toString())
    if (offset >= topBarHeight) {
        topBar.addClass('force-show')
    } else {
        topBar.removeClass('force-show')
    }
})