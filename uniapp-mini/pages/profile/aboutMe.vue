<!-- 关于作者 -->
<template>
  <view class="about">
    <like-fx ref="likeFx" :width="width" :height="height"></like-fx>
	<view class="titleZ text-center align-center">
		<text class="text-bold" style="font-size: 28px;">关于我们</text>
		<view class="contentZ">
			<text class="text-xl">
				青海青一色电子科技有限公司成立于2013年8月27日，注册资金200万，坐落于青海西宁市，是一家专业软件定制开发科研型公司，不限于小程序/APP开发、网站建设、应用业务系统、物联网开发等业务领域。以设计、研发、运维、开源于一体立足于互联网行业；<br><br>
				
			</text>
			
		
		</view>
		
		<view class="contentZ">
		
			<text class="text-xl" style="margin-top: 30rpx;">
				联系人：程序员星哥
				电话：15609712256（微信同号）
			</text>
		</view>
	</view>
  </view>
</template>

<script>
  import LikeFx from '@/components/likeFx/likeFx.vue'
  export default {
    components: {
      LikeFx
    },
    data() {
      return {
        animation_timer: null, // 动画定时器
        width: 375,
        height: 1920
      }
    },
    onLoad() {
      this._startLikeAnimation();
    },
	onUnload() {
		clearTimeout(this.animation_timer)
	},
    methods: {
      _startLikeAnimation() {
        this.animation_timer = setInterval(() => {
          this.$refs.likeFx.likeClick()
        }, 300)
      }
    }
  }
</script>

<style scoped>
	.align-center {
		text-align: center;
	}
	.titleZ{
		width: 750rpx;
		font-size: 42rpx;
		margin-top: 60rpx;
	}
	.contentZ{
		width: 650rpx;
		margin: 10rpx auto 0;
		text-align: left;
	}

  .about-bg {
    background-size: cover;
    width: 100vw;
    height: 100vh;
    justify-content: center;
    flex-direction: column;
    color: #fff;
  }

  .edit-fixed {
    position: fixed;
    width: 100%;
    bottom: 0;
    z-index: 1024;
    box-shadow: 0 1rpx 6rpx rgba(0, 0, 0, 0.1);
  }

  .detail-imgs image {
    width: 100%;
    float: left;
    /* height:400rpx; 不定高了*/
    border: 0;
    padding: 0;
    margin: 0
  }

  .share-img {
    position: fixed;
    padding: 10rpx;
    width: 100rpx;
    height: 100rpx;
    /* top: 680rpx; */
    bottom: 200rpx;
    right: 20rpx;
    z-index: 1024;
    opacity: 0.8;
    box-shadow: 0rpx 8rpx 30rpx 0rpx rgba(0, 0, 0, 0.3);
    border: none;
  }

  .about {
    margin: 0;
    width: 100%;
    height: 100vh;
	padding-top: 20%;
    color: #fff;
    /* background: linear-gradient(-120deg, #F15BB5, #9A5CE5, #01BEFF, #00F5D4); */
    background: linear-gradient(-120deg, #0976ea, #c471f5, #f956b6, #ea7e0a);
    background-size: 500% 500%;
    animation: gradientBG 15s ease infinite;
  }

  @keyframes gradientBG {
    0% {
      background-position: 0% 50%;
    }

    50% {
      background-position: 100% 50%;
    }

    100% {
      background-position: 0% 50%;
    }
  }

  .container {
    width: 100%;
    position: absolute;
    text-align: center;
  }

  .like-fx {
    position: fixed;
    right: 0;
    z-index: 1024;
    pointer-events: none;
    /* background-color: red; */
  }
</style>
