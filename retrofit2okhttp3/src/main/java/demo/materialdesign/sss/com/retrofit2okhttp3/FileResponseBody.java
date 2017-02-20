/*
 * Copyright (c) 2017.   Sss
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.materialdesign.sss.com.retrofit2okhttp3;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by sunshaoshuai on 2017/2/20.
 */
public class FileResponseBody extends ResponseBody {
    /**
     * 实际请求体
     */
    private ResponseBody responseBody;

    /**
     * 下载回调接口
     */
    private DownloadCallBack callback;

    /**
     * 资源的数据流
     */
    private BufferedSource mBufferedSource;

    public FileResponseBody(ResponseBody responseBody, DownloadCallBack callback) {
        super();
        this.responseBody = responseBody;
        this.callback = callback;
    }

    @Override
    public BufferedSource source() {
        if (mBufferedSource == null) {
            // 返回缓冲从源读取的新源。 返回的源将对其内存中缓冲区执行批量读取。 无论您在何处阅读源代码，都可以使用它，以获得符合人体工程学和高效的数据访问。
            // Returns a new source that buffers reads from source.
            // The returned source will perform bulk reads into its in-memory buffer.
            // Use this wherever you read a source to get an ergonomic and efficient access to data.
            mBufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return mBufferedSource;
    }

    /**
     * 数据内容的长度
     *
     * @return
     */
    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    /**
     * 数据内容的类型
     *
     * @return
     */
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    /**
     * 回调进度接口
     * <p>
     * 将资源对象输出到另一个资源对象，在read的时候进行下载进度的更新
     *
     * @param source 资源对象
     * @return Source
     */
    private Source source(Source source) {
        return new ForwardingSource(source) {
            //总长度
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                callback.onDownloading(totalBytesRead, responseBody.contentLength());
                return bytesRead;
            }
        };
    }
}
