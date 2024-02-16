<template>
	<view>
		
		<u-card v-for="(item,index) in listData" :key="index" :foot-border-top="false" margin="8rpx" padding="0 5rpx" @click="toCentent(item.id)">
				<view class="" slot="body">			
					<view class="u-body-item u-flex u-row-between u-p-b-1 u-p-t-1">
						<view class="u-body-item-title u-line-2">{{ item.title }}</view>
						<image :src="item.image" mode="aspectFill"></image>
					</view>
				</view>
				<view class="timeFooter" slot="foot"><u-icon name="calendar" size="28" color="#ff9900"></u-icon>{{ item.createTime | date}}</view>
			</u-card>

		<view class="text"  style="padding: 0 20rpx;margin-top: 6rpx;">
			<view><text></text></view>
			<view style="text-align: right;color: #909399;font-size: 13px;"><text></text></view>
		</view>	
	
	</view>
</template>

<script>
	import config from "@/common/config.js" // 全局配置文件
	export default {
		data() {
			return {
				newId:null,
				listData: [],
				pageNum: 1,
				pageSize: 20,
				scrollTop: 0,
				reload: false,
				loadStatus: 'loadmore',
				status: 'more',
				total:0,
				userId:null
			};
		},
		onLoad(option) {		
			this.newId = option.id; //上个页面传递的参数,本页面没有用到。
			this.getList();
		},
		onPageScroll(e) {
		    this.scrollTop = e.scrollTop;
		},
		onReachBottom() {
		    this.loadStatus = 'loading';
		    // 获取数据
			this.getList()
		},
		// 下拉刷新
		onPullDownRefresh() {
			this.pageNum = 1
			this.$refs.uWaterfall.clear();
			// 获取数据
			this.getList();
			// 关闭刷新
			uni.stopPullDownRefresh();
		},
		methods: {			
			getList() {
				let url = "/api/getArticelelist";
				let defaultData = {					
					pageNum: this.pageNum,
					pageSize: this.pageSize
				}
				this.$u.get(url, {...defaultData}).then(result => {
					 console.log(2222,result);
					const data = result.rows;
					
					let obj = []
					data.forEach(function(item, index) {
						obj.push({
							id: item.id,
							image: config.staticUrl + item.thumbnail,
							title: item.title,
							createTime: item.createTime
							
						})
					})
					
					this.listData = obj;

				});
			},
			
			toCentent(id){
				console.log(333,id)
				uni.navigateTo({
					url: '/pages/content/content?id=' + id
				}) 
			}
		}
	};
</script>

<style>	
	.text {
		background-color: #fff;
		line-height: 80rpx;
	}
	
	.u-card-wrap { 
			background-color: $u-bg-color;
			padding: 1px;
		}
		
		.u-body-item {
			font-size: 32rpx;
			color: #333;
			padding: 20rpx 10rpx;
		}
			
		.u-body-item image {
			width: 120rpx;
			flex: 0 0 120rpx;
			height: 120rpx;
			border-radius: 8rpx;
			margin-left: 12rpx;
		}
		.timeFooter{padding: 5rpx 12rpx;}
</style>
