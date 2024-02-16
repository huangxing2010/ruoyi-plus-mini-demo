<template>
	<view>
		<view class="u-content">
			<view class="title">{{articeInfo.title}}</view>
			<view class="byus">
				<span>作者:{{articeInfo.createBy}}</span>
				<span>发布时间:{{articeInfo.createTime | date}}</span>
			
			</view>
			<u-image width="100%" height="600rpx" :src="thumbnail" v-if="thumbnail != ''"></u-image>
			<u-gap height="30" ></u-gap>
			<u-parse :html="articeInfo.content"></u-parse>
			
			
		</view>
		<u-divider color="#606266">已经到底了</u-divider>
	</view>
</template>

<script>
	import config from "@/common/config.js" // 全局配置文件
	export default {
		data() {
			return {
				articeId: 0,
				articeInfo: {},
				thumbnail: ''
			
			}
		},
		onLoad(option) {
			let articeId = parseInt(option.id)
			this.getArticeInfo(articeId)
		},
		methods:{
			getArticeInfo(articeId){
				let data = {
					articleId: articeId
				}
				let url = "/api/getArticleCentent";
					this.$u.get(url, data).then(res => {
						console.log(4444232,res)
						if(res.code == 200){
							this.articeInfo = res.data
							this.thumbnail = config.staticUrl + res.data.thumbnail
							
							
						}
					})
				
				
				
				
			}
			
		}
	}
</script>


<style lang="scss" scoped>
    .u-content {
        padding: 24rpx;
    }
	.u-content .title{text-align: center;font-size: 36rpx;line-height: 48rpx;}
	.u-content .interlayer{line-height: 48rpx; padding-top: 24rpx;}
	.byus{padding: 20rpx;color: #ccc; text-align: center;}
	.byus span{padding: 0 20rpx;}
</style>

