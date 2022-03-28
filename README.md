TestChartView

## Using

``` xml
<com.example.mylibrary.chart_view.ChartView
android:id="@+id/chart"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_alignParentTop="true"
android:layout_marginTop="50dp"
app:isFrameNeeded="true"
app:lineColor="@color/purple_200"
app:lineWidth="@dimen/line_width"
app:point="@drawable/graph_point" />
```
### Display data:

for setting data use the method:
``` kotlin
fun setData(dataList: List<InputData>)
```


### Customizable parameters:

```lineColor``` - color of a chart line

```lineWidth``` - width of chart line

```frameLineColor``` - color of a lines of a frame

```isFrameNeeded``` - draw or not draw the frame

```point``` - setting drawable to a point


