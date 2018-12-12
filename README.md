# Common Utils

## ShortNetAddressUtils

- 根据传入的 **url**，通过访问百度短链接的接口， 将其转换成短的URL

```java
String getShortURL(String originURL)
```

## BeanUtils

- 复制两个对象的值

```java
void copyBean(Object source, Object to);
```

- 将下划线命名的对象值复制到驼峰命名的对象

```java
void copyBeanToCamelNaming(Object source, Object to);
```

- 将下划线命名的对象值批量复制到驼峰命名的对象

```java
void batchConvertToCamel(List<Object> objectList, List<Object> objectVOList, String className);
```

- JSON 格式的 toString 方法

```java
String toString(Object object);
```

## AesUtils, DesUtils, Md5Utils

- encrypt

```java
// AES
String encrypt(String key, String initVector, String value);
// DES
String encrypt(String str);
```

- decrypt

```java
// AES
String decrypt(String key, String initVector, String encrypted);
// DES
String decrypt(String str);
```

- getMd5

```java
String getMd5(String str);
```

## PageCalculatorUtils

```java
int calculatorRowIndex(int pageIndex, int pageSize);
```

## QrCodeUtils

```java
public class QrCodeArgs {
    private int height;

    private int width;

    Map<EncodeHintType, Object> hints;
}

BitMatrix generateQRCode(String content, HttpServletResponse response, QrCodeArgs qrCodeArgs)
```

## HttpServletRequestUtils

```java
int getInt(HttpServletRequest request, String key);
long getLong(HttpServletRequest request, String key);
double getDouble(HttpServletRequest request, String key);
boolean getBoolean(HttpServletRequest request, String key);
String getString(HttpServletRequest request, String key);
```

