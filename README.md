# async-io-kotlin in Kotlin

[![GitHub link](https://img.shields.io/badge/GitHub-KotlinMania%2Fasync--io--kotlin-blue.svg)](https://github.com/KotlinMania/async-io-kotlin)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kotlinmania/async-io-kotlin)](https://central.sonatype.com/artifact/io.github.kotlinmania/async-io-kotlin)
[![Build status](https://img.shields.io/github/actions/workflow/status/KotlinMania/async-io-kotlin/ci.yml?branch=main)](https://github.com/KotlinMania/async-io-kotlin/actions)

This is a Kotlin Multiplatform line-by-line transliteration port of [`smol-rs/async-io`](https://github.com/smol-rs/async-io).

**Original Project:** This port is based on [`smol-rs/async-io`](https://github.com/smol-rs/async-io). All design credit and project intent belong to the upstream authors; this repository is a faithful port to Kotlin Multiplatform with no behavioural changes intended.

### Porting status

This is an **in-progress port**. The goal is feature parity with the upstream Rust crate while providing a native Kotlin Multiplatform API. Every Kotlin file carries a `// port-lint: source <path>` header naming its upstream Rust counterpart so the AST-distance tool can track provenance.

---

## Upstream README — `smol-rs/async-io`

> The text below is reproduced and lightly edited from [`https://github.com/smol-rs/async-io`](https://github.com/smol-rs/async-io). It is the upstream project's own description and remains under the upstream authors' authorship; links have been rewritten to absolute upstream URLs so they continue to resolve from this repository.

## async-io

[![Build](https://github.com/smol-rs/async-io/actions/workflows/ci.yml/badge.svg)](
https://github.com/smol-rs/async-io/actions)
[![License](https://img.shields.io/badge/license-Apache--2.0_OR_MIT-blue.svg)](
https://github.com/smol-rs/async-io)
[![Cargo](https://img.shields.io/crates/v/async-io.svg)](
https://crates.io/crates/async-io)
[![Documentation](https://docs.rs/async-io/badge.svg)](
https://docs.rs/async-io)

Async I/O and timers.

This crate provides two tools:

* `Async`, an adapter for standard networking types (and [many other] types) to use in
  async programs.
* `Timer`, a future that expires at a point in time.

For concrete async networking types built on top of this crate, see [`async-net`].

[many other]: https://github.com/smol-rs/async-io/tree/master/examples
[`async-net`]: https://docs.rs/async-net

## Implementation

The first time `Async` or `Timer` is used, a thread named "async-io" will be spawned.
The purpose of this thread is to wait for I/O events reported by the operating system, and then
wake appropriate futures blocked on I/O or timers when they can be resumed.

To wait for the next I/O event, the "async-io" thread uses [epoll] on Linux/Android/illumos,
[kqueue] on macOS/iOS/BSD, [event ports] on illumos/Solaris, and [IOCP] on Windows. That
functionality is provided by the [`polling`] crate.

However, note that you can also process I/O events and wake futures on any thread using the
`block_on()` function. The "async-io" thread is therefore just a fallback mechanism
processing I/O events in case no other threads are.

[epoll]: https://en.wikipedia.org/wiki/Epoll
[kqueue]: https://en.wikipedia.org/wiki/Kqueue
[event ports]: https://illumos.org/man/port_create
[IOCP]: https://learn.microsoft.com/en-us/windows/win32/fileio/i-o-completion-ports
[`polling`]: https://docs.rs/polling

## Examples

Connect to `example.com:80`, or time out after 10 seconds.

```rust
use async_io::{Async, Timer};
use futures_lite::{future::FutureExt, io};

use std::net::{TcpStream, ToSocketAddrs};
use std::time::Duration;

let addr = "example.com:80".to_socket_addrs()?.next().unwrap();

let stream = Async::<TcpStream>::connect(addr).or(async {
    Timer::after(Duration::from_secs(10)).await;
    Err(io::ErrorKind::TimedOut.into())
})
.await?;
```

## License

Licensed under either of

 * Apache License, Version 2.0 ([LICENSE-APACHE](https://github.com/smol-rs/async-io/blob/HEAD/LICENSE-APACHE) or http://www.apache.org/licenses/LICENSE-2.0)
 * MIT license ([LICENSE-MIT](https://github.com/smol-rs/async-io/blob/HEAD/LICENSE-MIT) or http://opensource.org/licenses/MIT)

at your option.

#### Contribution

Unless you explicitly state otherwise, any contribution intentionally submitted
for inclusion in the work by you, as defined in the Apache-2.0 license, shall be
dual licensed as above, without any additional terms or conditions.

---

## About this Kotlin port

### Installation

```kotlin
dependencies {
    implementation("io.github.kotlinmania:async-io-kotlin:0.1.0")
}
```

### Building

```bash
./gradlew build
./gradlew test
```

### Targets

- macOS arm64
- Linux x64
- Windows mingw-x64
- iOS arm64 / simulator-arm64 (Swift export + XCFramework)
- JS (browser + Node.js)
- Wasm-JS (browser + Node.js)
- Android (API 24+)

### Porting guidelines

See [AGENTS.md](AGENTS.md) and [CLAUDE.md](CLAUDE.md) for translator discipline, port-lint header convention, and Rust → Kotlin idiom mapping.

### License

This Kotlin port is distributed under the same Apache-2.0 license as the upstream [`smol-rs/async-io`](https://github.com/smol-rs/async-io). See [LICENSE](LICENSE) (and any sibling `LICENSE-*` / `NOTICE` files mirrored from upstream) for the full text.

Original work copyrighted by the async-io authors.  
Kotlin port: Copyright (c) 2026 Sydney Renee and The Solace Project.

### Acknowledgments

Thanks to the [`smol-rs/async-io`](https://github.com/smol-rs/async-io) maintainers and contributors for the original Rust implementation. This port reproduces their work in Kotlin Multiplatform; bug reports about upstream design or behavior should go to the upstream repository.
