const topBar = $('#top-bar')
topBar.css('opacity', 0)
const height = topBar.css("height")
let lockShow = false

$(window).scroll(() => {
    const offset = $(window).scrollTop()
    if(offset >= height) {
        showTopBar()
        lockShow = true
    } else {
        lockShow = false
    }
})

// function forceShow() {
//     anime({
//         targets: '#top-bar',
//         opacity: 1,
//         translateY: 0,
//         duration: 0,
//     })
// }

function hideTopBar() {
    if(lockShow) return
    anime({
        targets: '#top-bar',
        opacity: 0,
        translateY: -10,
        duration: 1500,
    })
}

function showTopBar() {
    if(lockShow) return
    anime({
        targets: '#top-bar',
        opacity: 1,
        translateY: 0,
        duration: 1500,
    })
}


topBar.mouseenter(e => {
    showTopBar()
})

topBar.mouseleave(e => {
    hideTopBar()
})