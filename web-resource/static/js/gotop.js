const goTopBtn = $('#go-top-btn')
goTopBtn.click(e => {
    e.preventDefault();
    $('body, html').animate({
        scrollTop: 0,
    }, 1000);
})

$(window).scroll(() => {
    const offset = parseInt($(window).scrollTop())
    if (offset >= 800) {
        goTopBtn.css('z-index', 1000)
        goTopBtn.css('opacity', 0.5)
    } else {
        goTopBtn.css('z-index', -1000)
        goTopBtn.css('opacity', 0)
    }
})