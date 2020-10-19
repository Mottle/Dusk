// const topBar = $('#top-bar')
// topBar.css('opacity', 0)
// const topBarMaxOpacity = 0.7
// const height = topBar.css("height")
// let lockTopBarShown = false
//
// $(window).scroll(() => {
//     const offset = $(window).scrollTop()
//     if(offset >= height) {
//         showTopBar()
//         lockTopBarShown = true
//     } else {
//         lockTopBarShown = false
//     }
// })
//
// function hideTopBar() {
//     if(lockTopBarShown) return
//     anime({
//         targets: '#top-bar',
//         opacity: 0,
//         // translateY: -10,
//         duration: 2000,
//     })
// }
//
// function showTopBar() {
//     if(lockTopBarShown) return
//     anime({
//         targets: '#top-bar',
//         opacity: topBarMaxOpacity,
//         translateY: 0,
//         duration: 2000,
//     })
// }
//
//
// topBar.mouseenter(e => {
//     showTopBar()
// })
//
// topBar.mouseleave(e => {
//     hideTopBar()
// })