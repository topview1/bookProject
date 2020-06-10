/**
 * 实现自动轮播图
 */

  function autoplay() {
            var index = 0;  //标记当前图片索引
            var imgs = document.getElementsByClassName("box_img");
            var left = document.getElementById("box_left");
            var right = document.getElementById("box_right");
            var buttons = document.getElementsByClassName("button");

            var f;
            //每两秒自动跳转,自动轮播方法
            function TimeColltroer() {//因为setInterVal没重启的方法，在套个方法，在点击触发里调用
                f = setInterval(function () {
                    //还原imgs的opacity属性
                    if (index == (imgs.length - 1)) {
                        index = -1;
                        //隐藏所有图片
                        for (var i = 0; i < imgs.length; i++) {
                            imgs[i].style.opacity = "0";
                        }
                    }
                    index++;
                    imgs[0].style.opacity = "0";
                    imgs[index].style.opacity = "1";

                    //改变按钮的颜色
                    for (var i = 0; i < imgs.length; i++) {
                        buttons[i].style.backgroundColor = "#ccc";
                    }
                    buttons[index].style.backgroundColor = "#fff";

                }, 2000)
            }
            TimeColltroer(); //开始自动轮播

            //点击左右按钮进行轮播
            left.onclick = function () {
                clearInterval(f); //在点击的时候清除自动轮播效果，避免体验感差
                if (index == 0) {
                    index = imgs.length - 1;

                } else {
                    index--;
                }
                //隐藏所有图片
                for (var i = 0; i < imgs.length; i++) {
                    imgs[i].style.opacity = "0";
                }
                imgs[index].style.opacity = "1";

                //改变按钮的颜色
                for (var i = 0; i < imgs.length; i++) {
                    buttons[i].style.backgroundColor = "#ccc";
                }
                buttons[index].style.backgroundColor = "#fff";
                TimeColltroer();  //开始自动轮播
            }

            right.onclick = function () {
                clearInterval(f);
                if (index == imgs.length - 1) {
                    index = 0;

                } else {
                    index++;
                }
                //隐藏所有图片
                for (var i = 0; i < imgs.length; i++) {
                    imgs[i].style.opacity = "0";
                }
                imgs[index].style.opacity = "1";

                //改变按钮的颜色
                for (var i = 0; i < imgs.length; i++) {
                    buttons[i].style.backgroundColor = "#ccc";
                }
                buttons[index].style.backgroundColor = "#fff";
                TimeColltroer(); //开始自动轮播
            }

            //圆圈进行轮播

            for (var i = 0; i < imgs.length; i++) {
                buttons[i].index = i; //存入对象的index属性
                buttons[i].addEventListener("click", function () {
                    index = this.index;
                    clearInterval(f);  //停止自动轮播
                    //隐藏所有图片
                    for (var i = 0; i < imgs.length; i++) {
                        imgs[i].style.opacity = "0";
                    }
                    imgs[index].style.opacity = "1";

                    //改变按钮的颜色
                    for (var i = 0; i < imgs.length; i++) {
                        buttons[i].style.backgroundColor = "#ccc";
                    }
                    this.style.backgroundColor = "#fff";
                    TimeColltroer();  //开始自动轮播
                });
            }
        }

        autoplay();