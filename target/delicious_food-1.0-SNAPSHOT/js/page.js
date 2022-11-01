$.ajax({
    type:'get',
    url:'img'+location.search,
    success: function(body) {
        let img = document.querySelector('.img');
        img.src=body.image;
        let cnt = document.querySelector('.content');
        cnt.innerHTML=body.content;
        }
    }
)