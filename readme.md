# java-dcm2jpg
用於 node-java-bridge 的 dcm2jpg 功能
讓 node.js 可以透過 `Dcm2JpgExecutor` 將 DICOM 檔案轉換成圖檔

## Develop Note
1. VM Options 需要加入以下訊息
```bash
--add-opens
java.desktop/javax.imageio.stream=ALL-UNNAMED
--add-opens
java.base/java.io=ALL-UNNAMED
```
> [ref](https://github.com/dcm4che/dcm4che/issues/1403)
