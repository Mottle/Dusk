
const goTopBtn = $('#go-top-btn')
goTopBtn.click(e => {
    e.preventDefault();
    $('body, html').animate({
        scrollTop: 0,
    }, 1000);
})