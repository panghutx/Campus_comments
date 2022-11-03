let btn = document.querySelector('#submit');
let title = document.querySelector('#title');
let score = document.querySelector('#score');
let content = document.querySelector('#content');
let bodyDiv = document.querySelector('#bodyDiv');

//加载服务器数据到文件
function load() {
    $.ajax({
        type: 'get',
        url: 'food',
        contentType: 'json/application',
        success: function (body) {
            for (let single of body) {
                let id = document.createElement('p');
                id.innerHTML=single.commentId;
                id.className='commentId';
                id.style.display='none';
                bodyDiv.appendChild(id);
                //将数据展示到页面上
                let coreDiv = document.createElement('div');
                coreDiv.className = 'card h-100';
                bodyDiv.appendChild(coreDiv);

                let midDiv = document.createElement('div');
                midDiv.className = 'card-body';
                coreDiv.appendChild(midDiv);

                let h3 = document.createElement('h3');
                h3.className = 'score';
                h3.innerHTML = single.score + "分";
                h3.style.display = 'inline-block'
                midDiv.appendChild(h3);

                let rec = document.createElement('h5');
                rec.style.display = 'inline-block';
                rec.style.marginLeft = '60px';
                rec.className = 'recVal'+single.commentId;
                rec.innerHTML = '助力值：' + single.recommend;
                midDiv.appendChild(rec);


                let h5 = document.createElement('h5');
                h5.className = 'card-title';
                h5.innerHTML = single.title;
                midDiv.appendChild(h5);

                let p = document.createElement('p');
                p.className = 'card-text';
                p.innerHTML = single.content;
                midDiv.appendChild(p);

                let upDown = document.createElement('div');
                upDown.style.display='flex';
                coreDiv.appendChild(upDown);


                let call = document.createElement('button');
                call.type = 'button';
                call.className = 'btn btn-lg btn-primary recommend';
                call.style.width='50%';
                call.innerHTML = '赞一下👍';

                let down = document.createElement('bottom');
                down.type = 'button';
                down.className = 'btn btn-lg btn-danger down';
                down.style.width='50%';
                down.innerHTML='踩一下👎';
            
                upDown.appendChild(call);
                upDown.appendChild(down);

                let smlDiv = document.createElement('div');
                smlDiv.className = 'card-footer';
                coreDiv.appendChild(smlDiv);

                let small = document.createElement('small');
                small.className = 'text-muted';
                small.innerHTML = dateFormat(single.time);
                smlDiv.appendChild(small);

                
                call.onclick=function(){
                    $.ajax({
                        type:'post',
                        url:'recommend',
                        data:JSON.stringify({
                            commentId:single.commentId,
                            score:single.score,
                            recommend:single.recommend
                        }),
                        success:function(body){
                            rec.innerHTML = '助力值：' + body.recommend;
                        }
                    })
                    alert("感谢你的助力")

                    
                }

                down.onclick=function(){
                    $.ajax({
                        type:'post',
                        url:'down',
                        data:JSON.stringify({
                            commentId:single.commentId,
                            score:single.score,
                            recommend:single.recommend
                        }),
                        success:function(body){
                            rec.innerHTML = '助力值：' + body.recommend;
                        }
                    })

                    alert("感谢你的助力")
                }

                

            }
        }


    })
}

//获取排行榜前5名
function top5(){
    $.ajax({
        type:'get',
        url:'top',
        success:function(body){
            i=1;
            for(let top of body){
                
                let tit=document.querySelector('#tit'+i);
                let rec=document.querySelector('#rec'+i);
                i++;
                tit.innerHTML=top.title;
                rec.innerHTML=top.recommend;
            }
        }
    })
}

//获取排行榜后5名
function down5(){
    $.ajax({
        type:'get',
        url:'down',
        success:function(body){
            i=1;
            for(let top of body){
                
                let tit=document.querySelector('#downTit'+i);
                let rec=document.querySelector('#downRec'+i);
                i++;
                tit.innerHTML=top.title;
                rec.innerHTML=top.recommend;
            }
        }
    })
}

top5();
down5();

load();

//将表单内容提交到服务器
btn.onclick = function () {

    //捕获并检测输入内容
    //querySelectorAll可以捕获所有input，存放到数组

    //let inputs = document.querySelectorAll('input');
    //console.log(inputs);
    // let title = inputs[1].value;
    // let score = inputs[2].value;
    // let content = inputs[3].value;

    if (title.value == "" || score.value == "" || content.value == "") {
        alert("输入不能为空");
        return;
    }
    //将数据发送给服务器
    $.ajax({
        type: 'post',
        url: 'food',
        contentType: 'json/application',
        data: JSON.stringify({
            title: title.value,
            score: score.value,
            content: content.value,
            time: null
        }),
        success: function (body) {
            console.log(body);
        }


    })
    alert("感谢你的推荐");
    location.reload();

}




//时间转换函数
function dateFormat(timeStampMS) {
    var date = new Date(timeStampMS);
    var year = date.getFullYear(),
        month = date.getMonth() + 1, //月份是从0开始的
        day = date.getDate(),
        hour = date.getHours(),
        min = date.getMinutes(),
        sec = date.getSeconds();
    var newTime = year + '-' +
        (month < 10 ? '0' + month : month) + '-' +
        (day < 10 ? '0' + day : day) + ' ' +
        (hour < 10 ? '0' + hour : hour) + ':' +
        (min < 10 ? '0' + min : min) + ':' +
        (sec < 10 ? '0' + sec : sec);
    return newTime;
}