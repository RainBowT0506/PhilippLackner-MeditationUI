![image](https://hackmd.io/_uploads/B1rRRETLA.png)

# 功能概述
- 簡單功能：可變換 Chips、自訂底部導航
- 將只實現一個畫面，已足夠展示本次內容

# 資源準備
- 感謝設計師 Misha Dupliakin 提供的設計靈感
- 影片描述中提供 Dribble 連結
- 資源已包含在 GitHub 倉庫中：向觀眾提供倉庫連結下載初始項目
- 初始項目包含所有需要的資源，如向量圖形、字體、自訂顏色等

# 實作 Greeting Section
## 初始化項目
- 在 Android Studio Canary 中打開項目
- 主活動（MainActivity）中無需修改，將在單獨的文件中創建 HomeScreen

## 創建 HomeScreen
- 在 ui package 中創建新的 Kotlin 類 HomeScreen
- 設計整個畫面，根組件為 Box 包含所有內容
- Box 中包含一個 Column 和底部導航視圖

## 設計 Greeting Section
### 定義 GreetingSection 組件
- 包含參數 name，默認為 "Philipp"
- 使用 Row 佈局，包含文本和搜索按鈕

### GreetingSection 組件詳解
- Row 配置：
  - 水平排列方式：兩端對齊 (Arrangement.SpaceBetween)
  - 垂直排列方式：垂直居中 (Alignment.CenterVertically)
  - 填充最大寬度並設置 15 dp 的內邊距
- Column 配置：
  - 垂直排列方式：垂直居中 (Arrangement.Center)
  - 包含兩個文本：
    - "Good morning, $name!" 使用 MaterialTheme.typography.h2 樣式
    - "We wish you have a good day!" 使用 MaterialTheme.typography.body1 樣式
  - Icon 組件配置：
    - 繪畫資源：使用 painterResource 引用 drawable 中的資源
    - 描述內容：設為 "Search"
    - 顏色：設為白色
    - 大小：設為 24 dp

## 整合 Greeting Section
- 將 GreetingSection 添加到 HomeScreen 的 Column 中

# 實作 Chip Section
## 定義 ChipSection 組件
- ChipSection 組件接收一個參數 `chips`，這是一個字串列表
- 設置狀態 `selectedChipIndex` 來顯示當前選中的 Chip
- 初始值設為 0，即第一個 Chip 被選中

### 定義狀態和導入函數
- 使用 `remember` 記住選中的 Chip 索引
- 導入 `setValue` 和 `getValue` 函數

## LazyRow 設計
### LazyRow 介紹
- LazyRow 是一個可滾動的橫向列表，並且能夠懶加載項目
- 無需參數，使用 `items` 塊來定義項目的數量和樣式

### 定義項目數量和樣式
- 項目數量為 chips 列表的大小
- 每個 Chip 的樣式是一個包含背景、圓角和文本的 Box

### Box 配置
- 設置 Box 的內邊距
  - 左邊距：15 dp
  - 上邊距：15 dp
  - 下邊距：15 dp
- 避免雙倍間距：只在起始設置邊距
- 設置 Box 為可點擊，點擊時更新選中的 Chip 索引

### 其他配置
- 設置圓角：10 dp
- 設置背景顏色：
  - 當前選中的 Chip 使用 `buttonBlue`
  - 未選中的 Chip 使用 `darkerButtonBlue`
- 設置所有邊距：15 dp 來推入文本至中間

## 文本配置
- 中心對齊文本
- 使用 Chip 列表中的對應文本
- 設置文本顏色為 `textWhite`（稍微比純白暗一些以提高可讀性）

## 整合 ChipSection
- 在 HomeScreen 的 Column 中添加 ChipSection
- 傳遞 Chip 列表：`"Sweet sleep"`, `"Insomnia"`, `"Depression"`

## 整合至 HomeScreen
- 在 HomeScreen 的 Column 中添加 ChipSection
- 傳遞 Chip 列表：
  ```kotlin
  ChipSection(
      chips = listOf("Sweet sleep", "Insomnia", "Depression")
  )
  ```



# 實作 Daily Thought Section
## 定義 CurrentMeditation 組件
- CurrentMeditation 組件接收一個參數 `color`，用於設定背景顏色，默認值為淺紅色

## 設計行 (Row) 佈局
### Row 配置
- 垂直居中 (verticalAlignment = Alignment.CenterVertically)
- 水平排列方式：兩端對齊 (horizontalArrangement = Arrangement.SpaceBetween)
- 設置內邊距：15 dp
- 設置圓角：10 dp
- 設置背景顏色：使用傳入的顏色
- 設置水平內邊距：15 dp，垂直內邊距：20 dp
- 設置填充最大寬度

### 文本配置
- 文本內容：`Daily Thought` 和 `Meditation`
- 將文本放入 Column 中，垂直排列
- 設置文本顏色為 `textWhite`

### 圖示 (Icon) 配置
- 使用 Box 包含圖示
- 設置內容居中
- 設置 Box 大小：40 dp
- 圓形剪裁 (clip to circle shape)
- 設置背景顏色為 `buttonBlue`
- 設置內邊距：10 dp
- 包含播放圖示，設置顏色為白色，大小為 16 dp

## 整合至 HomeScreen
- 在 HomeScreen 的 Column 中添加 CurrentMeditation 組件

# 概述
這段影片繼續展示如何使用 Jetpack Compose 製作冥想應用程式的介面，這部分主要介紹如何實現 Feature 區段。

# 實作 Feature Section
## 定義 FeatureSection 組件
- FeatureSection 組件接收一個參數 `features`，這是一個 Feature 資料類別的列表
- Feature 資料類別包含標題、圖示 ID、淺色、中間色和深色

### Feature 資料類別
```kotlin
data class Feature(
    val title: String,
    @DrawableRes val iconID: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
)
```

### FeatureSection 組件
- 使用 Column 組件
- 包含標題文字 "Features"
- 使用 LazyVerticalGrid 顯示 Features 列表
  - 設置兩列 (GridCells.Fixed(2))
  - 設置內邊距：左右各 7.5 dp，底部 100 dp 以避免底部導航欄重疊

## 定義 FeatureItem 組件
### BoxWithConstraints 配置
- 設置內邊距：7.5 dp
- 設置寬高比：1:1（方形）
- 圓角剪裁：10 dp
- 設置背景顏色為 feature.darkColor

### 繪製中間和淺色路徑
- 定義中間顏色路徑
  - 使用 Offset 定義路徑點
  - 使用 Path 繪製路徑並連接各點
- 定義淺顏色路徑，同樣使用 Offset 和 Path

### 畫布繪製路徑
- 使用 Canvas 繪製中間和淺色路徑
- 設置填充顏色為 feature.mediumColor 和 feature.lightColor

### Box 配置
- 設置填充最大尺寸
- 包含 Feature 標題文本
- 包含圖示
- 包含可點擊的 "Start" 按鈕

## 註釋實驗性 API
- 使用 `@OptIn(ExperimentalFoundationApi::class)` 註釋需要的功能


# 實作 BottomMenu Section 
## 定義 BottomMenuContent 資料類別
### 資料類別定義
- 包含標題和圖示 ID
## 定義 BottomMenu 組件
### BottomMenu 組件參數
- items: List<BottomMenuContent>
- modifier: Modifier，默認為 Modifier
- activeHighlightColor: Color，默認為 ButtonBlue
- activeTextColor: Color，默認為白色
- inactiveTextColor: Color，默認為 AquaBlue
- initialSelectedItemIndex: Int，默認為 0

### BottomMenu 組件實現
- 定義狀態 selectedItemIndex，初始值為 initialSelectedItemIndex
- 使用 Row 佈局排列項目
  - 水平排列方式：平均分佈 (Arrangement.SpaceAround)
  - 垂直居中排列
  - 填充最大寬度
  - 設置背景色為 DeepBlue
  - 設置內邊距：15 dp
## 定義 BottomMenuItem 組件
### BottomMenuItem 組件參數
- item: BottomMenuContent
- isSelected: Boolean，默認為 false
- activeHighlightColor: Color，默認為 ButtonBlue
- activeTextColor: Color，默認為白色
- inactiveTextColor: Color，默認為 AquaBlue
- onItemClick: () -> Unit

### BottomMenuItem 組件實現
- 使用 Column 佈局
  - 水平和垂直居中對齊
  - 設置點擊事件觸發 onItemClick
- 使用 Box 包含圖示
  - 圓角剪裁：10 dp
  - 設置背景顏色：選中時為 activeHighlightColor，未選中時為透明色
  - 設置內邊距：10 dp
- 使用 Icon 和 Text 顯示圖示和標題
  - 圖示顏色：選中時為 activeTextColor，未選中時為 inactiveTextColor
  - 圖示大小：20 dp
  - 標題顏色：選中時為 activeTextColor，未選中時為 inactiveTextColor
 

## 整合 BottomMenu 至 HomeScreen
- 在 HomeScreen 中添加 BottomMenu 組件
- 傳遞 BottomMenuContent 列表
 
 
# 外部資源
* [Dribble](https://dribbble.com/)
* [Meditation Mobile App](https://dribbble.com/shots/15822493-Meditation-Mobile-App)


# 關鍵字 
- **Jetpack Compose**：Google 推出的現代化 UI 工具包，用於構建 Android 應用程序的用戶界面。
- **Box**：一個 Jetpack Compose 組件，用於將其他組件組織在一起並進行對齊，允許重疊組件。
- **Column**：一個 Jetpack Compose 組件，用於垂直排列子組件。
- **Row**：一個 Jetpack Compose 組件，用於水平排列子組件。
- **Modifier**：Jetpack Compose 中的一個界面，用於修飾或調整組件的外觀和行為。
- **fillMaxSize**：一個 Modifier，用於將組件填滿父容器的最大尺寸。
- **padding**：一個 Modifier，用於設置組件內部的填充空間。
- **background**：一個 Modifier，用於設置組件的背景顏色。
- **horizontalArrangement**：設置 Row 組件內部子組件的水平排列方式。
- **verticalAlignment**：設置 Row 組件內部子組件的垂直對齊方式。
- **spaceBetween**：一種排列方式，用於在 Row 或 Column 組件內部的子組件之間創建等間距。
- **MaterialTheme**：Jetpack Compose 中的主題設置，用於統一應用程序的外觀和風格。
- **typography**：MaterialTheme 中的一部分，用於設置字體和文字樣式。
- **painterResource**：一個函數，用於加載資源文件中的圖片或向量圖。
- **contentDescription**：一個屬性，用於描述圖片內容，方便無障礙工具使用。
- **tint**：一個屬性，用於設置圖片的顏色濾鏡。
- **icon**：Jetpack Compose 中的一個組件，用於顯示圖片或向量圖標。
- **drawable**：Android 中的一種資源類型，用於存儲圖片或向量圖。
- **dp**：密度無關像素（Density-independent Pixels），用於設置視圖的尺寸。 
- **Jetpack Compose**：Google 推出的現代化 UI 工具包，用於構建 Android 應用程序的用戶界面。
- **Composable**：Jetpack Compose 中的一個函數，用於描述 UI 元素的結構和行為。
- **Box**：一個 Jetpack Compose 組件，用於將其他組件組織在一起並進行對齊，允許重疊組件。
- **Column**：一個 Jetpack Compose 組件，用於垂直排列子組件。
- **Row**：一個 Jetpack Compose 組件，用於水平排列子組件。
- **Modifier**：Jetpack Compose 中的一個界面，用於修飾或調整組件的外觀和行為。
- **padding**：一個 Modifier，用於設置組件內部的填充空間。
- **clip**：一個 Modifier，用於裁剪組件，使其具有特定的形狀。
- **roundedCornerShape**：一個形狀定義，用於設置組件的圓角。
- **background**：一個 Modifier，用於設置組件的背景顏色。
- **contentAlignment**：設置 Box 組件內部子組件的對齊方式。
- **text**：Jetpack Compose 中的一個組件，用於顯示文字。
- **remember**：Jetpack Compose 中用於記住組件狀態的函數。
- **mutableStateOf**：Jetpack Compose 中用於創建可變狀態的函數。
- **val**：Kotlin 中的關鍵字，用於聲明一個不可變的變量。
- **icon**：Jetpack Compose 中的一個組件，用於顯示圖片或向量圖標。
- **painterResource**：一個函數，用於加載資源文件中的圖片或向量圖。
- **contentDescription**：一個屬性，用於描述圖片內容，方便無障礙工具使用。
- **tint**：一個屬性，用於設置圖片的顏色濾鏡。
- **textStyle**：一個屬性，用於設置文字樣式，例如字體大小和顏色。 
- **Jetpack Compose**：Google 推出的現代化 UI 工具包，用於構建 Android 應用程序的用戶界面。
- **Composable**：Jetpack Compose 中的一個函數，用於描述 UI 元素的結構和行為。
- **Box**：一個 Jetpack Compose 組件，用於將其他組件組織在一起並進行對齊，允許重疊組件。
- **Column**：一個 Jetpack Compose 組件，用於垂直排列子組件。
- **Row**：一個 Jetpack Compose 組件，用於水平排列子組件。
- **Modifier**：Jetpack Compose 中的一個界面，用於修飾或調整組件的外觀和行為。
- **padding**：一個 Modifier，用於設置組件內部的填充空間。
- **clip**：一個 Modifier，用於裁剪組件，使其具有特定的形狀。
- **roundedCornerShape**：一個形狀定義，用於設置組件的圓角。
- **background**：一個 Modifier，用於設置組件的背景顏色。
- **contentAlignment**：設置 Box 組件內部子組件的對齊方式。
- **text**：Jetpack Compose 中的一個組件，用於顯示文字。
- **remember**：Jetpack Compose 中用於記住組件狀態的函數。
- **mutableStateOf**：Jetpack Compose 中用於創建可變狀態的函數。
- **val**：Kotlin 中的關鍵字，用於聲明一個不可變的變量。
- **LazyVerticalGrid**：一個 Jetpack Compose 組件，用於垂直滾動的網格布局。
- **ExperimentalFoundationApi**：一個註解，用於標記 Jetpack Compose 中的實驗性 API。
- **Feature**：一個 Kotlin 數據類，用於描述每個功能項的屬性，包括標題、圖標、顏色等。
- **AspectRatio**：一個屬性，用於設置組件的寬高比。
- **Canvas**：一個用於繪製自定義形狀的 Jetpack Compose 組件。
- **Path**：用於繪製自定義形狀的類，允許創建任意形狀。
- **offset**：一個類，用於指定圖形元素的位置。
- **drawPath**：一個 Canvas 函數，用於繪製路徑。
- **lineTo**：一個 Path 函數，用於繪製直線。
- **quadTo**：一個 Path 函數，用於繪製二次貝塞爾曲線。
- **standardQuadTo**：自定義擴展函數，用於簡化繪製二次貝塞爾曲線的操作。 
- **Jetpack Compose**：Google 推出的現代化 UI 工具包，用於構建 Android 應用程序的用戶界面。
- **Composable**：Jetpack Compose 中的一個函數，用於描述 UI 元素的結構和行為。
- **Box**：一個 Jetpack Compose 組件，用於將其他組件組織在一起並進行對齊，允許重疊組件。
- **Column**：一個 Jetpack Compose 組件，用於垂直排列子組件。
- **Row**：一個 Jetpack Compose 組件，用於水平排列子組件。
- **Modifier**：Jetpack Compose 中的一個界面，用於修飾或調整組件的外觀和行為。
- **padding**：一個 Modifier，用於設置組件內部的填充空間。
- **clip**：一個 Modifier，用於裁剪組件，使其具有特定的形狀。
- **roundedCornerShape**：一個形狀定義，用於設置組件的圓角。
- **background**：一個 Modifier，用於設置組件的背景顏色。
- **contentAlignment**：設置 Box 組件內部子組件的對齊方式。
- **text**：Jetpack Compose 中的一個組件，用於顯示文字。
- **remember**：Jetpack Compose 中用於記住組件狀態的函數。
- **mutableStateOf**：Jetpack Compose 中用於創建可變狀態的函數。
- **val**：Kotlin 中的關鍵字，用於聲明一個不可變的變量。
- **clickable**：一個 Modifier，用於設置組件的點擊行為。
- **forEachIndexed**：Kotlin 中的一個擴展函數，用於遍歷集合中的每個元素並提供其索引。
- **painterResource**：一個函數，用於加載資源文件中的圖片或向量圖。
- **contentDescription**：一個屬性，用於描述圖片內容，方便無障礙工具使用。
- **tint**：一個屬性，用於設置圖片的顏色濾鏡。
- **color**：Jetpack Compose 中的顏色類，用於設置組件的顏色。
- **size**：一個 Modifier，用於設置組件的大小。
- **bottom menu item**：底部導航視圖中的單個項目，包含圖標和文字。
- **data class**：Kotlin 中的一種特殊類型，用於存儲數據並自動生成常見函數如 `toString()`、`equals()` 和 `hashCode()`。
- **default modifier**：預設的修飾符，用於設置組件的初始狀態和行為。
- **active highlight color**：選中狀態下的高亮顏色。
- **active text color**：選中狀態下的文字顏色。
- **inactive text color**：未選中狀態下的文字顏色。
- **initial selected item index**：初始選中的項目索引。 
