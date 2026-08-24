=== Deep Analysis: tmp/async-io (rust) -> src/commonMain/kotlin (kotlin) ===

Scanning source codebase (rust)...
Codebase: tmp/async-io (rust)
  Files: 23
  Total imports: 143
  Most depended: tests.async (8 dependents)

Scanning target codebase (kotlin)...
Codebase: src/commonMain/kotlin (kotlin)
  Files: 16
  Total imports: 54

Comparing codebases...
Computing AST similarities...

=== Codebase Comparison Report ===

Source: tmp/async-io (23 files)
Target: src/commonMain/kotlin (16 files)
Scoring invariant: FnSim is required function body/parameter similarity. Class/type and symbol parity are reported beside it; whole-file shape is diagnostic only.

Matched:   10 files
Unmatched: 13 source, 6 target

=== Matched Files (by porting priority) ===

Source                        Target                        FnSim     Dependents FunctionParityTypeParity  Priority
 --------------------------------------------------------------------------------------------------------------------------
reactor                       reactor.Reactor [PROVENANCE-FALLBACK]0.20      1          19/25         10/11       1073608.0 
lib                           asyncio.Lib [PROVENANCE-FALLBACK]0.18      0          29/57         3/6         316308.2  
driver                        asyncio.Driver [PROVENANCE-FALLBACK]0.05      0          2/9           0/2         91109.5   
os.kqueue                     os.Kqueue [PROVENANCE-FALLBACK]0.19      0          10/14         5/8         72208.1   
os.windows                    os.Windows [PROVENANCE-FALLBACK]0.17      0          8/11          1/4         61508.3   
reactor.windows               reactor.Windows [PROVENANCE-FALLBACK]0.44      0          6/6           1/1         705.6     
reactor.kqueue                reactor.Kqueue [PROVENANCE-FALLBACK]0.33      0          5/5           1/1         606.7     
reactor.unix                  reactor.Unix [PROVENANCE-FALLBACK]0.40      0          5/5           1/1         606.0     
os.unix                       os.Unix [PROVENANCE-FALLBACK] 0.13      0          1/1           0/0         108.7     
os                            os.Os [PROVENANCE-FALLBACK]   1.00      0          0/0           0/0         0.0       

=== Function and Symbol Details ===

reactor -> reactor.Reactor [PROVENANCE-FALLBACK]
  similarity: 0.20, priority: 1073608.0, dependents: 1
  provenance warning: port-lint provenance header matched only after fallback normalization: `reactor.rs` vs expected `reactor.rs`
  functions: 19/25 matched (target total: 25, required body score: 0.20)
  missing functions: readable_owned, writable_owned, ready, poll, fmt, drop
  types: 10/11 matched (target total: 12)
  missing types: Output

lib -> asyncio.Lib [PROVENANCE-FALLBACK]
  similarity: 0.18, priority: 316308.2, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
  functions: 29/57 matched (target total: 31, required body score: 0.18)
  missing functions: drop, poll, poll_next, as_raw_fd, as_fd, try_from, as_raw_socket, as_socket, poll_read, poll_read_vectored, poll_write, poll_write_vectored, poll_flush, poll_close, bind, accept, incoming, connect, peek, recv_from, peek_from, send_to, recv, send, pair, unbound, setup_networking, convert_path_to_socket_address
  types: 3/6 matched (target total: 3)
  missing types: Output, Item, Error

driver -> asyncio.Driver [PROVENANCE-FALLBACK]
  similarity: 0.05, priority: 91109.5, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `driver.rs` vs expected `driver.rs`
  functions: 2/9 matched (target total: 3, required body score: 0.05)
  missing functions: unparker, main_loop, parker_and_waker, create, wake_by_ref, wake, drop
  types: 0/2 matched (target total: 1)
  missing types: BlockOnWaker, CallOnDrop

os.kqueue -> os.Kqueue [PROVENANCE-FALLBACK]
  similarity: 0.19, priority: 72208.1, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `os/kqueue.rs` vs expected `os/kqueue.rs`
  functions: 10/14 matched (target total: 10, required body score: 0.19)
  missing functions: as_raw_fd, as_fd, try_from, registration
  types: 5/8 matched (target total: 6)
  missing types: Error, Ready, Output

os.windows -> os.Windows [PROVENANCE-FALLBACK]
  similarity: 0.17, priority: 61508.3, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `os/windows.rs` vs expected `os/windows.rs`
  functions: 8/11 matched (target total: 14, required body score: 0.17)
  missing functions: as_raw_handle, as_handle, try_from
  types: 1/4 matched (target total: 4)
  missing types: Error, Ready, Output

reactor.windows -> reactor.Windows [PROVENANCE-FALLBACK]
  similarity: 0.44, priority: 705.6, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `reactor/windows.rs` vs expected `reactor/windows.rs`
  functions: 6/6 matched (target total: 6, required body score: 0.44)
  missing functions: none
  types: 1/1 matched (target total: 4)
  missing types: none

reactor.kqueue -> reactor.Kqueue [PROVENANCE-FALLBACK]
  similarity: 0.33, priority: 606.7, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `reactor/kqueue.rs` vs expected `reactor/kqueue.rs`
  functions: 5/5 matched (target total: 5, required body score: 0.33)
  missing functions: none
  types: 1/1 matched (target total: 5)
  missing types: none

reactor.unix -> reactor.Unix [PROVENANCE-FALLBACK]
  similarity: 0.40, priority: 606.0, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `reactor/unix.rs` vs expected `reactor/unix.rs`
  functions: 5/5 matched (target total: 8, required body score: 0.40)
  missing functions: none
  types: 1/1 matched (target total: 2)
  missing types: none

os.unix -> os.Unix [PROVENANCE-FALLBACK]
  similarity: 0.13, priority: 108.7, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `os/unix.rs` vs expected `os/unix.rs`
  functions: 1/1 matched (target total: 4, required body score: 0.13)
  missing functions: none
  types: 0/0 matched (target total: 2)
  missing types: none

os -> os.Os [PROVENANCE-FALLBACK]
  similarity: 1.00, priority: 0.0, dependents: 0
  provenance warning: port-lint provenance header matched only after fallback normalization: `os.rs` vs expected `os.rs`
  functions: 0/0 matched (target total: 0, required body score: 1.00)
  missing functions: none
  types: 0/0 matched (target total: 1)
  missing types: none


=== Provenance Header Fallbacks ===

These files were paired only after normalization; fix the port-lint source header.
  - reactor -> reactor.Reactor: port-lint provenance header matched only after fallback normalization: `reactor.rs` vs expected `reactor.rs`
    proposed: // port-lint: source reactor.rs
  - lib -> asyncio.Lib: port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
    proposed: // port-lint: source lib.rs
  - driver -> asyncio.Driver: port-lint provenance header matched only after fallback normalization: `driver.rs` vs expected `driver.rs`
    proposed: // port-lint: source driver.rs
  - os.kqueue -> os.Kqueue: port-lint provenance header matched only after fallback normalization: `os/kqueue.rs` vs expected `os/kqueue.rs`
    proposed: // port-lint: source os/kqueue.rs
  - os.windows -> os.Windows: port-lint provenance header matched only after fallback normalization: `os/windows.rs` vs expected `os/windows.rs`
    proposed: // port-lint: source os/windows.rs
  - reactor.windows -> reactor.Windows: port-lint provenance header matched only after fallback normalization: `reactor/windows.rs` vs expected `reactor/windows.rs`
    proposed: // port-lint: source reactor/windows.rs
  - reactor.kqueue -> reactor.Kqueue: port-lint provenance header matched only after fallback normalization: `reactor/kqueue.rs` vs expected `reactor/kqueue.rs`
    proposed: // port-lint: source reactor/kqueue.rs
  - reactor.unix -> reactor.Unix: port-lint provenance header matched only after fallback normalization: `reactor/unix.rs` vs expected `reactor/unix.rs`
    proposed: // port-lint: source reactor/unix.rs
  - os.unix -> os.Unix: port-lint provenance header matched only after fallback normalization: `os/unix.rs` vs expected `os/unix.rs`
    proposed: // port-lint: source os/unix.rs
  - os -> os.Os: port-lint provenance header matched only after fallback normalization: `os.rs` vs expected `os.rs`
    proposed: // port-lint: source os.rs

=== Missing from Target (need to port) ===

File                          Deps    Path
------------------------------------------------------------------------------
tests.async                   8       tests/async.rs
benches.io                    5       benches/io.rs
tests.block_on                1       tests/block_on.rs
tests.timer                   1       tests/timer.rs
benches.timer                 0       benches/timer.rs
build                         0       build.rs
examples.kqueue-process       0       examples/kqueue-process.rs
examples.linux-inotify        0       examples/linux-inotify.rs
examples.linux-timerfd        0       examples/linux-timerfd.rs
examples.unix-signal          0       examples/unix-signal.rs
examples.windows-command      0       examples/windows-command.rs
examples.windows-uds          0       examples/windows-uds.rs
tests.issue_182               0       tests/issue_182.rs

=== Porting Quality Summary ===

Matched by exact header:          0 / 10
Matched by provenance fallback:   10 / 10
Matched by name:                  0 / 10
Total TODOs in target: 0
Total lint errors:    10
Stub files:           0

=== Big Picture ===

- Missing files: 13
- Incomplete ports (similarity < 60%): 9
- Stub files: 0
- Files missing functions: 5 (total deficit: 48 functions)
- Type definitions missing: 12
- Files missing tests: 0 (total deficit: 0 unported `#[test]` functions)
- Documentation coverage: 552 / 3302 lines (17%)

Primary focus: create missing files (highest deps first)

=== Files with Issues ===

File                          Similarity LineRatio  FunctionParityTests     TODOs Lint  Status
----------------------------------------------------------------------------------------------------
reactor.Reactor [PROVENANCE-  0.20       0.00       19/25         -         0     1     LOW_SIM
  missing functions: `readable_owned`, `writable_owned`, `ready`, `poll`, `fmt`, `drop`
  missing types: `Output`
asyncio.Lib [PROVENANCE-FALL  0.18       0.00       29/57         -         0     1     LOW_SIM
  missing functions: `drop`, `poll`, `poll_next`, `as_raw_fd`, `as_fd`, `try_from`, `as_raw_socket`, `as_socket`, `poll_read`, `poll_read_vectored`, `poll_write`, `poll_write_vectored`, `poll_flush`, `poll_close`, `bind`, `accept`, `incoming`, `connect`, `peek`, `recv_from`, `peek_from`, `send_to`, `recv`, `send`, `pair`, `unbound`, `setup_networking`, `convert_path_to_socket_address`
  missing types: `Output`, `Item`, `Error`
asyncio.Driver [PROVENANCE-F  0.05       0.00       2/9           -         0     1     LOW_SIM
  missing functions: `unparker`, `main_loop`, `parker_and_waker`, `create`, `wake_by_ref`, `wake`, `drop`
  missing types: `BlockOnWaker`, `CallOnDrop`
os.Kqueue [PROVENANCE-FALLBA  0.19       0.00       10/14         -         0     1     LOW_SIM
  missing functions: `as_raw_fd`, `as_fd`, `try_from`, `registration`
  missing types: `Error`, `Ready`, `Output`
os.Windows [PROVENANCE-FALLB  0.17       0.00       8/11          -         0     1     LOW_SIM
  missing functions: `as_raw_handle`, `as_handle`, `try_from`
  missing types: `Error`, `Ready`, `Output`
reactor.Windows [PROVENANCE-  0.44       0.00       6/6           -         0     1     LINT
reactor.Kqueue [PROVENANCE-F  0.33       0.00       5/5           -         0     1     LOW_SIM
reactor.Unix [PROVENANCE-FAL  0.40       0.00       5/5           -         0     1     LINT
os.Unix [PROVENANCE-FALLBACK  0.13       0.00       1/1           -         0     1     LOW_SIM
os.Os [PROVENANCE-FALLBACK]   1.00       0.00       -             -         0     1     LINT

=== Porting Recommendations ===

Incomplete ports (similarity < 60%): 9
Missing files: 13

Incomplete ports to complete:
  reactor                        similarity=0.20 function_parity=19/25 dependents=1
    missing functions: `readable_owned`, `writable_owned`, `ready`, `poll`, `fmt`, `drop`
    missing types: `Output`
  lib                            similarity=0.18 function_parity=29/57 dependents=0
    missing functions: `drop`, `poll`, `poll_next`, `as_raw_fd`, `as_fd`, `try_from`, `as_raw_socket`, `as_socket`, `poll_read`, `poll_read_vectored`, `poll_write`, `poll_write_vectored`, `poll_flush`, `poll_close`, `bind`, `accept`, `incoming`, `connect`, `peek`, `recv_from`, `peek_from`, `send_to`, `recv`, `send`, `pair`, `unbound`, `setup_networking`, `convert_path_to_socket_address`
    missing types: `Output`, `Item`, `Error`
  driver                         similarity=0.05 function_parity=2/9 dependents=0
    missing functions: `unparker`, `main_loop`, `parker_and_waker`, `create`, `wake_by_ref`, `wake`, `drop`
    missing types: `BlockOnWaker`, `CallOnDrop`
  os.kqueue                      similarity=0.19 function_parity=10/14 dependents=0
    missing functions: `as_raw_fd`, `as_fd`, `try_from`, `registration`
    missing types: `Error`, `Ready`, `Output`
  os.windows                     similarity=0.17 function_parity=8/11 dependents=0
    missing functions: `as_raw_handle`, `as_handle`, `try_from`
    missing types: `Error`, `Ready`, `Output`
  reactor.windows                similarity=0.44 function_parity=6/6 dependents=0
  reactor.kqueue                 similarity=0.33 function_parity=5/5 dependents=0
  reactor.unix                   similarity=0.40 function_parity=5/5 dependents=0
  os.unix                        similarity=0.13 function_parity=1/1 dependents=0

=== Missing Files (by Dependents) ===

Source File                   Expected Target                       Dependents Path
-----------------------------------------------------------------------------------------------------------------------
tests.async                   tests.Async                           8          tests/async.rs
benches.io                    benches.Io                            5          benches/io.rs
tests.block_on                tests.BlockOn                         1          tests/block_on.rs
tests.timer                   tests.Timer                           1          tests/timer.rs
benches.timer                 benches.Timer                         0          benches/timer.rs
build                         Build                                 0          build.rs
examples.kqueue-process       examples.Kqueue-process               0          examples/kqueue-process.rs
examples.linux-inotify        examples.Linux-inotify                0          examples/linux-inotify.rs
examples.linux-timerfd        examples.Linux-timerfd                0          examples/linux-timerfd.rs
examples.unix-signal          examples.Unix-signal                  0          examples/unix-signal.rs
examples.windows-command      examples.Windows-command              0          examples/windows-command.rs
examples.windows-uds          examples.Windows-uds                  0          examples/windows-uds.rs
tests.issue_182               tests.Issue182                        0          tests/issue_182.rs

=== Documentation Gaps ===

There is missing documentation that is hurting overall scoring.
Documentation coverage: 552 / 3302 lines (17%)
Files with >20% doc gap: 6

File                          Src Docs    Tgt Docs    Gap %     DocSim    DocAmt    DocEq     
----------------------------------------------------------------------------------------------
lib                           2446        135         94%       0.34      0.06      0.20      
os.kqueue                     270         60          77%       0.54      0.22      0.38      
os.windows                    202         56          72%       0.50      0.28      0.39      
os.unix                       78          40          48%       0.87      0.51      0.69      
driver                        38          12          68%       0.44      0.32      0.38      
reactor.windows               52          38          26%       0.61      0.73      0.67      

=== Generating Reports ===

✅ Generated: port_status_report.md
✅ Generated: high_priority_ports.md
✅ Generated: NEXT_ACTIONS.md
✅ Generated: port_lint_proposed_changes.md

📁 All reports generated successfully!
