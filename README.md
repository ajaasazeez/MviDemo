<h1>MVI News App</a></h1>
<p>This is a simple news app to showcase MVI architecture</p>
<p>The app uses the following libraries / topics:</p>
<ul>
	<li>Kotlin</li>
  <li>Koin</li>
	<li>Coroutines</li>
  <li>Room</li>
	<li>Retrofit2</li>
	<li>Glide</li>
	<li>ViewModels</li>
	<li>Repository pattern</li>
<br>
<p>The app displays space news from a open api https://api.spaceflightnewsapi.net/v3/articles and displays it in a recyclerview. The network response will be saved in cache so that it still loads data incase there is no internet.</p>
  <p>The ui package contains the MainActivity & MainFragment. The MainActivity have the progressbar and which implemensts DataStateListener which updatee the status of the progressbar. The MainFragment is where the list is populated in recyclerview and the API call is initiated from MainFragment</p>
  <p>Koin is used for dependency injection. There is network module which contails network related dependencies,database module which provide dao and database dependencies and a MainModule which provide dependencies of ViewModel  & Repository</p>
 <p> The state package contain two classes, MainViewState & MainStateEvent.
  The app only have a single model class that is NewsModel.
  The MainViewState class wraps around all the datamodels since we only have one data model we wrap NewsModel inside MainViewState class.
  
  MVI have event driven nature. In this app, when the app is opened. a state event is sent to the viewmodel .The viewmodel determines that it is a  getNewsEvent which then sends the request to repository.The repository accesses the network and gives news data back to the viewmodel i.e dataState. This dataState is observed in the fragment which sets the viewState in the viewModel. This viewState is observed in fragment and sets the view according to data.
   
   The MainRepository is where we send the getNews API.The newsDao and apiServcie dependencies were passed as constructors. When the API is success handleApiSuccessResponse() will be triggered and we save the response to cache with the help of coroutines and send the response back to viewModel. When the network request fails the function handleApiErrorResponse() will be triggered and fetch the data from cache and send the data baack to viewmodel. 
  </p>
<br>
<p></p>
