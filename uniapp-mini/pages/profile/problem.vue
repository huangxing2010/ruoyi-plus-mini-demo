<template>
	<view style="margin: 20rpx;">
		<u-navbar :is-back="true" title="常见问题" :border-bottom="false"></u-navbar>
		<u-collapse class="u-p-30">
			<u-collapse-item :title="item.head" v-for="(item, index) in itemList" :key="index" :open="item.open">
				{{item.body}}
			</u-collapse-item>
		</u-collapse>
	</view>
</template>

<script>
export default {
	data() {
		return {
			appUrl: false,
			itemList: [
				{
					head: "微信小程序可以实现什么功能？",
					body: "微信小程序是目前使用人群最多的手机终端应用，可以做业务逻辑交互、信息传递、授权登录等功能的开发，您在使用过程中若需要我们的帮助，请您联系技术人员",
					open: false,
				},
				{
					head: "技术支持电话是多少？",
					body: "联系电话：15609712256",
					open: false,
				},
				{
					head: "关于我们",
					body: "我们专注提供专业的软件产品和技术。为合作伙伴提供安全可靠的软件产品与解决方案，共同帮助终端用户实现业务创新、快速发展。" ,
					open: true,
				},
				{
					head: "加入我们",
					body: "邮箱：1398474457@qq.com",
					open: false,
				},
			],
		}
	},
	methods: {
		download(){
		},
		//判断是否微信登陆
		isWeiXinLogin() {
			// #ifdef H5
			let ua = window.navigator.userAgent.toLowerCase()
			if (ua.match(/MicroMessenger/i) == 'micromessenger') {
				return true;
			}
			// #endif
			return false;
		},
	}
};
</script>

<style>
	page {
		background: #fff;
	}
	
	.appUrl{
		padding: 20rpx;
	}
</style>
