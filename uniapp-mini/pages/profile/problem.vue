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
					head: " 本微信小程序可以实现什么功能？",
					body: "微信小程序是目前使用人群最多的手机终端应用，可以做业务逻辑交互、信息传递、授权登录等功能的开发；系统后台使用开源框架 ruoyi-vue-plus，详细的技术栈可以查看官网，集成小程序授权登录、换灯轮播、文章资讯、列表页面下拉刷新等功能，旨让开发者快速开发自己的业务系统",
					open: false,
				},{
					head: "为什么开源？",
					body: "苟利国家生死以，岂因祸福避趋之。漫漫人生路，总有许多困难和挑战。而我，愿意成为你的伙伴，一起战胜一切。因为你需要，我刚好在。",
					open: false,
				},
				{
					head: "遇到技术门槛怎么办",
					body: "JAVA一切皆对象，万事皆思路决定出路。没有bug的系统本身就是个bug，站在巨人的肩膀上您或许看的更远，一失足往往摔的更疼。若您有任何技术问题请联系我们，共同成长、共同进步！" ,
					open: true,
				},
				{
					head: "合作共赢",
					body: "世界上本没有路，走的人多了也便成了路；一个人也许走的很稳，但一群人必定走的会更远，期待与您合作共赢！",
					open: false,
				},
				{
					head: "技术支持电话是多少？",
					body: "联系电话：15609712256(微信同号)",
					open: false,
				}
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
