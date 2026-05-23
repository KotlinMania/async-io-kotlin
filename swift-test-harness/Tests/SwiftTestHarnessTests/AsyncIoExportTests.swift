import XCTest
import AsyncIo

// Smoke test for the Kotlin → Swift Export → SPM → swift test pipeline.
//
// The file's mere existence and successful compilation prove three layers
// of the pipeline:
//
//   1. `embedSwiftExportForXcode` produced `AsyncIo.swiftmodule/`
//      and the supporting KotlinRuntimeSupport / ExportedKotlinPackages /
//      KotlinRuntime swiftmodule bundles. If any of them were missing,
//      `import AsyncIo` above would fail at compile time.
//
//   2. The static archive `libAsyncIo.a` (produced by the
//      `linkSwiftExportBinaryDebugStaticMacosArm64` and
//      `mergeMacosDebugSwiftExportLibraries` tasks) supplied every
//      `__root____*` and `KotlinError`-related symbol the Swift modules
//      reference. If the archive were missing or empty, this test
//      executable would fail to link with "undefined symbols for
//      architecture arm64".
//
//   3. The Kotlin `swiftExport { moduleName = "AsyncIo" }` and
//      `flattenPackage = "io.github.kotlinmania.asyncio"` configuration in
//      build.gradle.kts produced a module name that's both syntactically
//      valid as a Swift identifier and reachable from this Package.swift
//      via the `AsyncIoLibrary` product.
//
// Add more meaningful per-API tests below as the Swift Export surface
// grows. For now the import + a single passing assertion is the
// canary that the pipeline is green for this repo.
final class AsyncIoExportTests: XCTestCase {
    func testSwiftModuleLoads() throws {
        XCTAssertTrue(true, "AsyncIo swift module imported cleanly")
    }
}
