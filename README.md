# MyPracticeWork
自己练手的项目项目集合。
##第一个项目是基于SwipeRefreshLayout和RecyclerView的刷新。
这个练手的item不仅仅有通过SwipeRefreshLayout的下拉刷新和侦听RecyclerView的上拉到最后一个时实现加载更多的操作，还有实现了当加载到一定程度是提示没有更多数据的功能。
###总结下第一个项目实现的功能有
1，通过SwipeRefreshLayout的下拉刷新。   
2，RecyclerView的上拉到最后一个时实现加载更多的操作。  
3，RecyclerView实现不同的ViewHolder。  
4，实现没有更多数据的提示。  
具体效果可参看ZEALER android客户端。这是一个成熟的实践。  
[视频效果](https://www.youtube.com/watch?v=KnFqYPlmd1Y&feature=youtu.be) 

##第二个项目是基于流式布局来实现标签的添加和删除。
这个项目可以增加对自定义Viewgroup的认识，更深一步的了解ViewGroup中onmeasure和onlayout函数。
###总结下第二个项目实现的功能有
1，在自定义的viewgroup中通过调用onMeasure函数中对子view的测量来获得自身的大小；调用onLayout在确定自身大小的前提下实现对子view的布局。  
2，通过子view的点击事件来实现标签的添加和删除。  
3，具体效果参照微信对标签的操作。




 
