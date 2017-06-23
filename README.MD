This is an example of [Twitter's Finatra](https://twitter.github.io/finatra/) implementation.

The service is used for querying [GIPHY](https://giphy.com/) APIs.

For current implementation, it covers :
* **SEARCH API**
    * URL : /giphy/search
    * Params :
        * offset : the first item offset, default: 0
        * limit : max number of item returned, default: 10
        * query : query for search (eg: sad dog), required
        * show_nsfw : whether should return nsfw post, default: false


\* more function might be implemented in future  