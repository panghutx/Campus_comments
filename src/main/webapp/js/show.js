$.ajax({
    type:'get',
    url:'img',
    success: function(body) {
        for(let data of body){
            
            let coreDiv = document.querySelector('#nav');
            let div = document.createElement('div');
            div.className='col-sm-6 col-md-4';
            coreDiv.appendChild(div);
            let thumDiv = document.createElement('div');
            thumDiv.className='thumbnail';
            div.appendChild(thumDiv);
            let img = document.createElement('img');
            img.src=data.image;
            img.style.width='240px';
            img.style.height='200px';
            img.alt='图片'
            thumDiv.appendChild(img);
            thumDiv.appendChild(img);

            let caption = document.createElement('div');
            caption.className='caption';
            thumDiv.appendChild(caption);
            let pContent = document.createElement('p');
            pContent.innerHTML=data.content;
            caption.appendChild(pContent);
            let pLink = document.createElement('p');
            caption.appendChild(pLink);
            let a = document.createElement('a');
            a.href='page.html?'+'id='+data.id;
            a.className='btn btn-primary';
            a.role='button';
            a.innerHTML='查看详情';
            pLink.appendChild(a);
        }
    }
})
