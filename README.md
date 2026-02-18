# 🎨 Android Reusable Components

A collection of production-ready, reusable Android UI components and utilities built with **Jetpack Compose** and modern Android development practices.

[![](https://img.shields.io/badge/Kotlin-1.9+-purple.svg)](https://kotlinlang.org)
[![](https://img.shields.io/badge/Compose-Latest-blue.svg)](https://developer.android.com/jetpack/compose)
[![](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)

## 📋 What's Inside

This library provides:

- **🎨 UI Components**: Ready-to-use Compose components (buttons, cards, inputs, etc.)
- **🏗️ Design System**: Consistent theming, colors, and typography
- **⚡ Utilities**: Common Android utilities and extensions
- **🔄 Coroutine Dispatchers**: Simplified coroutine management
- **📱 Sample App**: Live examples and usage demonstrations

## 🚀 Features

✅ **100% Jetpack Compose** - Modern declarative UI  
✅ **Kotlin First** - Idiomatic Kotlin code  
✅ **Material Design 3** - Latest design guidelines  
✅ **Type Safe** - Strongly typed components  
✅ **Well Documented** - Comprehensive KDoc comments  
✅ **Production Ready** - Battle-tested in real apps  

## 📦 Modules

### `android-design-library`
Core UI components and design system.

### `base`
Base classes and utilities for Android development.

### `common-coroutine-dispatchers`
Simplified coroutine dispatcher management.

### `sample-app`
Demo application showcasing all components.

## 🔧 Setup

### Add to your project

**Step 1:** Add JitPack repository to your root `build.gradle`:

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}
```

**Step 2:** Add the dependency:

```kotlin
dependencies {
    implementation("com.github.saibabu0524:android-resuable-components:1.0.0")
}
```

## 💻 Usage Examples

### Using Design Components

```kotlin
import com.saibabui.design.*

@Composable
fun MyScreen() {
    CustomButton(
        text = "Click Me",
        onClick = { /* Handle click */ }
    )
    
    CustomCard(
        title = "Card Title",
        content = "Card content here"
    )
}
```

### Using Coroutine Dispatchers

```kotlin
import com.saibabui.dispatchers.*

class MyViewModel : ViewModel() {
    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            // Background work
            val data = repository.getData()
            
            withContext(Dispatchers.Main) {
                // Update UI
                _uiState.value = data
            }
        }
    }
}
```

## 🏗️ Building from Source

Clone the repository:

```bash
git clone https://github.com/saibabu0524/android-resuable-components.git
cd android-resuable-components
```

Build the project:

```bash
./gradlew build
```

Run the sample app:

```bash
./gradlew :sample-app:installDebug
```

## 📱 Sample App

The included sample app demonstrates all components with live examples. 

To run:
1. Open project in Android Studio
2. Select `sample-app` configuration
3. Run on device or emulator

## 🧪 Testing

Run unit tests:

```bash
./gradlew test
```

Run instrumentation tests:

```bash
./gradlew connectedAndroidTest
```

## 📚 Documentation

Full documentation is available in the [docs](./docs) folder.

- [Component Guide](./docs/components.md)
- [Theming Guide](./docs/theming.md)
- [Architecture](./docs/architecture.md)

## 🛠️ Tech Stack

- **Language:** Kotlin 1.9+
- **UI:** Jetpack Compose
- **Architecture:** Clean Architecture, MVVM
- **DI:** Hilt (optional)
- **Build:** Gradle with Kotlin DSL
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 34 (Android 14)

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Sai Babu**

- GitHub: [@saibabu0524](https://github.com/saibabu0524)
- LinkedIn: [Sai Babu](https://linkedin.com/in/sai-babu)

## ⭐ Support

If you find this library useful, please consider giving it a star ⭐ on GitHub!

## 📝 Changelog

See [CHANGELOG.md](CHANGELOG.md) for release history.

---

**Built with ❤️ using Kotlin and Jetpack Compose**
