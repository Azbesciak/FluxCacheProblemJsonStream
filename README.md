# FluxCacheProblemJsonStream
Shows problem with JSON stream + cache combination

Port 8080
endpoints:
- `/works` - works without caching, simple sequence, `application/stream+json`.
- `/doesNot` - does not work because of cache (I suppose)
- `/worksWithFlatMap` - same as above, but after `cache` identity `flatMap` is added
- `/worksWithJsonResult` - first option with cache, but `produce = application/json`.
