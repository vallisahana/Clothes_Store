# Clothes Store

An e-commerce Android application written in Kotlin.

# Android components

* RecyclerViewAdapter
* REST API
* Jetpack compose 
* MVVM 
* Glide
* Material Design

# REST API
JSON is based on a subset of the JavaScript Programming Language. Representative State Transfer (REST) is a client-server architectural style that uses the HTTP protocol in a simple and effective way. Systems that adhere to REST practices are often referred to as RESTful interfaces.

# Android MVVM Architecture
MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables.

* Model: It represents the data and the business logic of the Android Application. It consists of the business logic - local and remote data source, model classes, repository.

* View: It consists of the UI Code(Activity, Fragment), XML. It sends the user action to the ViewModel but does not get the response back directly. To get the response, it has to subscribe to the observables which ViewModel exposes to it.

* ViewModel: It is a bridge between the View and Model(business logic). It does not have any clue which View has to use it as it does not have a direct reference to the View. So basically, the ViewModel should not be aware of the view who is interacting with. It interacts with the Model and exposes the observable that can be observed by the View.

# Jetpack compose
Jetpack Compose is Android's modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.

# Material Design
Material design is a comprehensive guide for visual, motion, and interaction design across platforms and devices. To use material design in your Android apps, follow the guidelines defined in the material design specification and use the new components and styles available in the material design support library.

