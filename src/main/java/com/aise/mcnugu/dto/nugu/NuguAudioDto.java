package com.aise.mcnugu.dto.nugu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class NuguAudioDto {

    private String type;
    private AudioItem audioItem;


    @Builder
    public NuguAudioDto(String type, AudioItem audioItem) {
        this.type = type;
        this.audioItem = audioItem;
    }


    @Builder
    @Getter @Setter
    public static class AudioItem {

        private Stream stream;


        @Builder
        @Getter @Setter
        public static class Stream {
            private String url;
            private int offsetInMilliseconds;
        }
    }
}
