(function(doc){
    let t = null,//视频开始播放循环
        dt = null,
        pt = null;//视频准备好播放循环
    let MswVideo = function (dom, opt) {
        this.videoBox = doc.getElementById(dom); //最外层盒子
        this.vid = this.videoBox.getElementsByClassName('video-tag')[0];//视频控件
        this.oPlayBtn = this.videoBox.getElementsByClassName('play-img')[0];//播放按钮
        this.oCurrentTime = this.videoBox.getElementsByClassName('current-time')[0];//播放时长
        this.oDuration = this.videoBox.getElementsByClassName('duration')[0];//总时长
        this.oRateArea = this.videoBox.getElementsByClassName('playrate-area')[0];//倍速外层盒子
        this.oRateBtn = this.oRateArea.getElementsByClassName('playrate')[0],//倍速按钮
        this.oRateList = this.oRateArea.getElementsByClassName('playrate-list')[0];//倍速选项外层盒子
        this.oRateBtns = this.oRateList.getElementsByClassName('item');//倍速所有选项
        this.oVolumeArea = this.videoBox.getElementsByClassName('volume-area')[0];//音量调节外层盒子
        this.oVolumeBtn = this.oVolumeArea.getElementsByClassName('volume-img')[0];//音量调节按钮
        this.oVolumeBar = this.oVolumeArea.getElementsByClassName('volume-bar')[0];//音量滑块外层盒子
        this.oVolumeSlideBar = this.oVolumeArea.getElementsByClassName('slide-bar')[0];//音量滑块背景
        this.oVolumeSlider = this.oVolumeArea.getElementsByClassName('volume-slide')[0];//音量滑块
        this.oVolumeRound = this.oVolumeSlider.getElementsByClassName('round')[0];//音量滑块按钮点
        this.oFullscreenBtn = this.videoBox.getElementsByClassName('fullscreen-img')[0];//全屏按钮
        this.oVidHeader = this.videoBox.getElementsByClassName('vid-hd')[0];//标题
        this.oControlBar = this.videoBox.getElementsByClassName('control-bar')[0];//底部包裹盒子
        this.oProgressBar = this.videoBox.getElementsByClassName('progress-bar')[0];//进度条包裹盒子
        this.oPlayProgress = this.oProgressBar.getElementsByClassName('play-progress')[0];//进度预加载背景条滑块
        this.oPreloadProgress = this.oProgressBar.getElementsByClassName('preload-progress')[0];//进度条滑块
        this.oPlayRound = this.oPlayProgress.getElementsByClassName('round')[0];//进度条滑块按钮
        this.oRateBtnsLen = this.oRateBtns.length;//倍速按钮长度
        this.src = opt.src;//视频路径
        this.autoplay = opt.autoplay && false;//自动播放
        this.preload = this.autoplay ? false : (opt.preload || false);//预加载
        this.volume = opt.volume / 100 || 1;//音量 0-1
        this.loop = opt.loop && false;//自动播放
 
        this.muted =false; //是否静音
        this.volumeBarShow = false; //是否显示音量滑块
        this.isFullScreen = false; //是否全屏
        this.init();//初始化
 
    }    
    MswVideo.prototype = {
        init(){
            this.setOptions();//设置默认参数
           
            this.bindEvent();//事件绑定
        },
        //设置默认设置
        setOptions(){
            this.vid.src = this.src;
            this.vid.autoplay = this.autoplay;
            this.vid.preload = this.preload;
            this.vid.loop = this.loop;
            this.setVolume(this.volume,true);
        },
        //设置音量
        setVolume(volume,isChangeBar){
            this.vid.volume = volume;
            isChangeBar && (this.oVolumeSlideBar.style.height = (volume * 100)+'%')
        },
        //事件绑定
        bindEvent(){
            this.vid.addEventListener('canplay',this._canplay.bind(this),false);//准备好了播放事件
            this.vid.addEventListener('playing',this._playing.bind(this),false);//开始播放事件
            this.vid.addEventListener('waiting',this._waiting.bind(this),false);//视频加载事件
            this.vid.addEventListener('error',this._error.bind(this),false);//视频错误事件
            this.vid.addEventListener('ended',this._ended.bind(this),false);//视频播放完毕事件
            this.vid.addEventListener('loadstart', this._loadstart.bind(this), false);//加载事件  
            
            this.oPlayBtn.addEventListener('click',this.playVideo.bind(this),false);//点击播放按钮
            this.oRateBtn.addEventListener('click',this.showRateList.bind(this,true),false);//点击倍速按钮
            this.oRateArea.addEventListener('mouseleave',this.showRateList.bind(this,false),false);//点击倍速外层盒子
            this.oRateList.addEventListener('click',this.setPlayRate.bind(this),false);//点击倍速选项
            this.oVolumeBtn.addEventListener('click',this.btnSetVolume.bind(this),false);//点击设置音量
            this.oVolumeArea.addEventListener('mouseleave',this.showVolumeBar.bind(this,false),false);//移出音量滑块
            this.oVolumeRound.addEventListener('mousedown',this.slideVolumeBar.bind(this),false);//拖动滑块
            this.oFullscreenBtn.addEventListener('click',this.setFullScreen.bind(this),false);//点击全屏
            this.videoBox.addEventListener('mousemove',this.showControlBar.bind(this),false);//移入移出显示工具栏
            this.oProgressBar.addEventListener('click',this.progressClick.bind(this),false);//点击设置进度
            this.oPlayRound.addEventListener('mousedown',this.progressChange.bind(this),false);//滑动设置进度
        },
        //准备好播放事件
        _canplay(){
            setTime(this.oDuration,this.vid.duration)//设置总时间进度
            removeVideoTip(this.videoBox);  //移除加载提示
            let _self = this,
                duration = this.vid.duration,
                preloadProgress = 0,
                progressBarWidth = this.oProgressBar.offsetWidth;//进度条总长度
            pt = setInterval(()=>{
                preloadProgress = _self.vid.buffered.end(0);//缓冲时的进度
                _self.oPreloadProgress.style.width = (_self.vid.buffered.end(0) / duration) * 100 + '%'//实际缓冲进度条宽度
                if(_self.oPreloadProgress.offsetWidth >= progressBarWidth){
                    clearInterval(pt);
                    pt = null;
                }
 
            },1000)    
        },
        //开始播放事件
        _playing(){
            this.setVideoState(true);//将按钮默认弄成暂停
            removeVideoTip(this.videoBox);
            let _self = this,
                duration = this.vid.duration,
                currentTime = 0,
                progressBarWidth = this.oProgressBar.offsetWidth;
            t = setInterval(()=>{
                currentTime = _self.vid.currentTime;//当前播放时间
                setTime(_self.oCurrentTime,currentTime)//给当前时间赋值
                _self.oPlayProgress.style.width = (currentTime / duration) * 100 + '%';
                if(_self.oPlayProgress.offsetWidth >= progressBarWidth){
                    clearInterval(t);
                    t = null;
                }
            },1000);    
        },
        //视频等待事件
        _waiting(){
            addVideoTip(this.videoBox,'loading')//设置加载图标
        },
        //视频播放错误事件
        _error(){
            removeVideoTip(this.videoBox);
            addVideoTip(this.videoBox,'error');
            clearInterval(t);
            clearInterval(pt);
            t = null;
            pt = null;
        },
        //视频播放完毕事件
        _ended(){
            removeVideoTip(this.videoBox);
            addVideoTip(this.videoBox,'ended')
        },
        //加载事件
        _loadstart(){
            removeVideoTip(this.videoBox);
            addVideoTip(this.videoBox,'loading')
        },
        //控制播放暂停按钮图标
        setVideoState(isPlaying){
            this.oPlayBtn.src = isPlaying ? '/images/icon2.png' : '/images/icon1.png'
        },
        //点击播放按钮
        playVideo(){
            if(this.vid.paused){
                this.oPlayBtn.src = '/images/icon2.png';
                this.vid.play();
            }else{
                this.oPlayBtn.src = '/images/icon1.png';
                this.vid.pause();
            }
        },
        //显示倍速选项
        showRateList(show){
            if(show){
                this.oRateList.className += ' show';
            }else{
                //this.oRateList.className = 'playrate-list';
            }
        },
        //设置倍速选项
        setPlayRate(er){
            let e = er || window.event,
                tar = e.target || e.srcElement,
                className = tar.className,
                rateBtn;
            if(className === 'rate-btn'){
                for(var i = 0; i< this.oRateBtnsLen;i++){
                    rateBtn = this.oRateBtns[i].getElementsByClassName('rate-btn')[0];
                    rateBtn.className = 'rate-btn';
                }
                this.vid.playbackRate = tar.getAttribute('data-rate');
                tar.className +=' current'
                this.showRateList(false);
            }  
            this.oRateList.className = 'playrate-list';
        },
        //点击音量图标
        btnSetVolume(){
            //如果为非静音非显示的情况下只进行显示
            if(!this.muted && !this.volumeBarShow){
                this.showVolumeBar(true);
            //如果为非静音显示情况下显示情况下进行静音且调整滑块高度
            }else if(!this.muted && this.volumeBarShow){
                this.setMuted(true);
                this.setVolume(0,true)
            //如果为静音情况下非现实情况下关闭静音且调整滑块高度    
            }else{
                this.setMuted(false);
                this.setVolume(this.volume,true);
            }
        },
        //显示音量滑块
        showVolumeBar(show){
            if(show){
                this.oVolumeBar.className += ' show';
                this.volumeBarShow = true;
            }else{
               
            }
        },    
        //设置是否静音
        setMuted(muted){
            if(muted){
                this.vid.muted = true;
                this.muted = true;
                this.oVolumeBtn.src = '/images/icon3.png';
                this.oVolumeBar.className = 'volume-bar';
                this.volumeBarShow = false;
            }else{
                this.vid.muted = false;
                this.muted = false;
                this.oVolumeBtn.src = '/images/icon4.png'
                 
            }
            
            
        },
        //设置滑块
        setVolume(volume,isChangeBar){
            this.vid.volume = volume;
            isChangeBar && (this.oVolumeSlider.style.height = (volume*100)+'%');
            
        },
        //点击全屏显示
        setFullScreen:function(){
            if(!this.isFullScreen){
                if (this.videoBox.requestFullscreen) {
                    this.videoBox.requestFullscreen();
                } else if (this.videoBox.mozRequestFullscreen) {
                    this.videoBox.mozRequestFullscreen();
                } else if (this.videoBox.msRequestFullscreen) {
                    this.videoBox.msRequestFullscreen();
                } else if (this.videoBox.oRequestFullscreen) {
                    this.videoBox.oRequestFullscreen();
                } else if (this.videoBox.webkitRequestFullscreen) {
                    this.videoBox.webkitRequestFullscreen();
                }
      
                this.isFullScreen = true;
                this.oFullscreenBtn.src = '/images/icon6.png';
            }else{
                if (doc.exitFullscreen) {
                    doc.exitFullscreen();
                } else if (doc.mozExitFullscreen) {
                    doc.mozExitFullscreen();
                } else if (doc.msExitFullscreen) {
                    doc.msExitFullscreen();
                } else if (doc.oExitFullscreen) {
                    doc.oExitFullscreen();
                } else if (doc.webkitExitFullscreen) {
                    doc.webkitExitFullscreen();
                }
      
                this.isFullScreen = false;
                this.oFullscreenBtn.src = '/images/icon5.png';
            }
        },
        //滑块滑动
        slideVolumeBar(er){
            let e = er || window.event,
                dy = e.pageY,//按下时的坐标
                my = 0,//移动时坐标
                disY = 0,//具体移动的距离
                sHeight = 0,//一共目前音量长度
                slideHeight = this.oVolumeSlider.offsetHeight,//实际长度
                volumeBarHeight = this.oVolumeSlideBar.offsetHeight,//总长度
                _mousemove = _mouseMove.bind(this),
                _mouseup = _mouseUp.bind(this);
            doc.addEventListener('mousemove',_mousemove,false);//移动事件
            doc.addEventListener('mouseup',_mouseup,false);//鼠标松开事件
            function _mouseMove(er){
                let e = er ||  window.event;
                my = e.pageY;
                disY = dy-my;
                sHeight = slideHeight + disY;
                if(sHeight < volumeBarHeight && sHeight > 0){
                    this.oVolumeSlider.style.height = sHeight + 'px';
                    this.setMuted(false);
                }else if(sHeight >= volumeBarHeight){
                    this.oVolumeSlider.style.height = volumeBarHeight + 'px';
                    sHeight = volumeBarHeight;
                    this.setMuted(false);
                }else if(sHeight <= 0){
                    this.oVolumeSlider.style.height = '0';
                    sHeight = 0;
                    this.setMuted(true);
                }
                this.volume =  (sHeight /volumeBarHeight).toFixed(1);
                this.setVolume(this.volume,false);
                this.volume = Number(this.volume) == 0 ? 0.5 : this.volume;
            }
            function _mouseUp(){
                doc.removeEventListener('mousemove', _mousemove, false);
                doc.removeEventListener('mouseup', _mouseup, false);
                this.oVolumeBar.className = 'volume-bar';
                this.volumeBarShow = false; 
            }
            
        },
        //进度条点击
        progressClick(er){
            let e = er || window.event;
            this.setPlayProgress(e.pageX);
        },
        //进度条变更
        setPlayProgress(pageX){
            let duration = this.vid.duration,
                curProgressBarWidth = pageX - this.videoBox.offsetLeft,//当前进度长度
                ratio = 0;
            if(curProgressBarWidth <= 0){
                ratio = 0;
            }else if(curProgressBarWidth >= this.oProgressBar.offsetWidth){
                ratio = 1;
            }else{
                ratio = curProgressBarWidth / this.oProgressBar.offsetWidth;
            }    
            this.vid.currentTime = ratio * duration;
            setTime(this.oCurrentTime,this.vid.currentTime);
            this.setVideoState(true);
            this.vid.play();
            this.oPlayProgress.style.width = ratio * 100 + '%';
        },
        //进度条滑动
        progressChange: function () {
            var _mousemove = _mouseMove.bind(this),
                _mouseup = _mouseUp.bind(this);
      
            doc.addEventListener('mousemove', _mousemove, false);
            doc.addEventListener('mouseup', _mouseup, false);
      
            function _mouseMove (e) {
              var  e = e || window.event; 
              this.setPlayProgress(e.pageX);
            }
      
            function _mouseUp () {
              doc.removeEventListener('mousemove', _mousemove, false);
              doc.removeEventListener('mouseup', _mouseup, false);
            }
        },
        //标题栏显示隐藏
        showControlBar: function () {
            clearTimeout(dt);
            dt = null;
            this.setControlBar(false);
  
            var _self = this;
  
            dt = setTimeout(function () {
            _self.setControlBar(true);
            }, 5000);
        },
        setControlBar: function (hide) {
            if (hide) {
                this.oVidHeader.className += ' hide';
                this.oControlBar.className += ' hide';
            } else {
                this.oVidHeader.className = 'vid-hd';
                this.oControlBar.className = 'control-bar';
            }
        },
    }
    //设置总时间进度
    function setTime(dom,time){
        dom.innerText = timeFormat(time)
    }
    //格式化时间
    function timeFormat(second){
        let h = parseInt(second / 3600),
            m = parseInt(parseInt(second % 3600) / 60),
            s = parseInt(parseInt(second % 3600) % 60),
            time = '';
        if(h == 0){
            if(m >= 10){
                if(s >= 10){
                    time = '00:'+ m + ':' + s;
                }else{
                    time = '00:' + m +':0' +s;
                }
            }else{
                if(s >= 10){
                    time = '00:0'+ m + ':' + s;
                }else{
                    time = '00:0' + m +':0' +s;
                }
            }
        }else{
            if (h < 10) {
                if (m >= 10) {
                    if (s >= 10) {
                        time = '0' + h + ':' + m + ':' + s;
                    } else {
                        time = '0' + h + ':' + m + ':0' + s;
                    }
                } else {
                    if (s >= 10) {
                        time = '0' + h + ':0' + m + ':' + s;
                    } else {
                        time = '0' + h + ':0' + m + ':0' + s;
                    }
                }
            } else {
                if (m >= 10) {
                    if(s >= 10) {
                time =  h + ':' + m + ':' + s;
                    } else {
                time =  h + ':' + m + ':0' + s;
                    }
                } else {
                    if(s >= 10) {
                time =  h + ':0' + m + ':' + s;
                    } else {
                time =  h + ':0' + m + ':0' + s;
                    }
                }
            }
        }    
        return time;
    }
    //移除加载中图标
    function removeVideoTip(dom){
        let oTip = doc.getElementsByClassName('video-tip')[0];
        oTip && dom.removeChild(oTip);
    }
    //设置加载中图标
    function addVideoTip(dom,type){
        let icon = '',
            text = '';
        switch(type){
            case 'loading':
               icon = '/images/icon7.png';
               text = '加载中';
               break;
            case 'error':
               icon = '/images/icon8.png';
               text = '播放错误';
               break;  
            case 'ended':
               icon = '/images/icon9.png';
               text = '播放完成';
               break;      
            default:
               break;    
        } 
        let oTip = doc.createElement('div');
        oTip.className = 'video-tip';
        oTip.innerHTML = '<img src ="' + icon + '" /><p>' + text + '</p>';
        dom.appendChild(oTip);  
    }
    window.MswVideo = MswVideo;    
})(document)