// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

package com.github.nicholasmoser.kaitai;

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.nio.charset.Charset;


/**
 * ID3v1.1 tag is a method to store simple metadata in .mp3 files. The
 * tag is appended to the end of file and spans exactly 128 bytes.
 * 
 * This type is supposed to be used on full .mp3 files, seeking to
 * proper position automatically. If you're interesting in parsing only
 * the tag itself, please use `id3v1_1::id3_v1_1_tag` subtype.
 * @see <a href="https://id3.org/ID3v1">Source</a>
 */
public class Id3v11 extends KaitaiStruct {
    public static Id3v11 fromFile(String fileName) throws IOException {
        return new Id3v11(new ByteBufferKaitaiStream(fileName));
    }

    public Id3v11(KaitaiStream _io) {
        this(_io, null, null);
    }

    public Id3v11(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public Id3v11(KaitaiStream _io, KaitaiStruct _parent, Id3v11 _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
        _read();
    }
    private void _read() {
    }

    /**
     * ID3v1.1 tag itself, a fixed size 128 byte structure. Contains
     * several metadata fields as fixed-size strings.
     * 
     * Note that string encoding is not specified by standard, so real
     * encoding used would vary a lot from one implementation to
     * another. Most Windows-based applications tend to use "ANSI"
     * (i.e. locale-dependent encoding, usually one byte per
     * character). Some embedded applications allow selection of
     * charset.
     */
    public static class Id3V11Tag extends KaitaiStruct {
        public static Id3V11Tag fromFile(String fileName) throws IOException {
            return new Id3V11Tag(new ByteBufferKaitaiStream(fileName));
        }

        public enum GenreEnum {
            BLUES(0),
            CLASSIC_ROCK(1),
            COUNTRY(2),
            DANCE(3),
            DISCO(4),
            FUNK(5),
            GRUNGE(6),
            HIP_HOP(7),
            JAZZ(8),
            METAL(9),
            NEW_AGE(10),
            OLDIES(11),
            OTHER(12),
            POP(13),
            RNB(14),
            RAP(15),
            REGGAE(16),
            ROCK(17),
            TECHNO(18),
            INDUSTRIAL(19),
            ALTERNATIVE(20),
            SKA(21),
            DEATH_METAL(22),
            PRANKS(23),
            SOUNDTRACK(24),
            EURO_TECHNO(25),
            AMBIENT(26),
            TRIP_HOP(27),
            VOCAL(28),
            JAZZ_FUNK(29),
            FUSION(30),
            TRANCE(31),
            CLASSICAL(32),
            INSTRUMENTAL(33),
            ACID(34),
            HOUSE(35),
            GAME(36),
            SOUND_CLIP(37),
            GOSPEL(38),
            NOISE(39),
            ALTERNROCK(40),
            BASS(41),
            SOUL(42),
            PUNK(43),
            SPACE(44),
            MEDITATIVE(45),
            INSTRUMENTAL_POP(46),
            INSTRUMENTAL_ROCK(47),
            ETHNIC(48),
            GOTHIC(49),
            DARKWAVE(50),
            TECHNO_INDUSTRIAL(51),
            ELECTRONIC(52),
            POP_FOLK(53),
            EURODANCE(54),
            DREAM(55),
            SOUTHERN_ROCK(56),
            COMEDY(57),
            CULT(58),
            GANGSTA(59),
            TOP_40(60),
            CHRISTIAN_RAP(61),
            POP_FUNK(62),
            JUNGLE(63),
            NATIVE_AMERICAN(64),
            CABARET(65),
            NEW_WAVE(66),
            PSYCHADELIC(67),
            RAVE(68),
            SHOWTUNES(69),
            TRAILER(70),
            LO_FI(71),
            TRIBAL(72),
            ACID_PUNK(73),
            ACID_JAZZ(74),
            POLKA(75),
            RETRO(76),
            MUSICAL(77),
            ROCK_N_ROLL(78),
            HARD_ROCK(79),
            FOLK(80),
            FOLK_ROCK(81),
            NATIONAL_FOLK(82),
            SWING(83),
            FAST_FUSION(84),
            BEBOB(85),
            LATIN(86),
            REVIVAL(87),
            CELTIC(88),
            BLUEGRASS(89),
            AVANTGARDE(90),
            GOTHIC_ROCK(91),
            PROGRESSIVE_ROCK(92),
            PSYCHEDELIC_ROCK(93),
            SYMPHONIC_ROCK(94),
            SLOW_ROCK(95),
            BIG_BAND(96),
            CHORUS(97),
            EASY_LISTENING(98),
            ACOUSTIC(99),
            HUMOUR(100),
            SPEECH(101),
            CHANSON(102),
            OPERA(103),
            CHAMBER_MUSIC(104),
            SONATA(105),
            SYMPHONY(106),
            BOOTY_BASS(107),
            PRIMUS(108),
            PORN_GROOVE(109),
            SATIRE(110),
            SLOW_JAM(111),
            CLUB(112),
            TANGO(113),
            SAMBA(114),
            FOLKLORE(115),
            BALLAD(116),
            POWER_BALLAD(117),
            RHYTHMIC_SOUL(118),
            FREESTYLE(119),
            DUET(120),
            PUNK_ROCK(121),
            DRUM_SOLO(122),
            A_CAPELLA(123),
            EURO_HOUSE(124),
            DANCE_HALL(125);

            private final long id;
            GenreEnum(long id) { this.id = id; }
            public long id() { return id; }
            private static final Map<Long, GenreEnum> byId = new HashMap<Long, GenreEnum>(126);
            static {
                for (GenreEnum e : GenreEnum.values())
                    byId.put(e.id(), e);
            }
            public static GenreEnum byId(long id) { return byId.get(id); }
        }

        public Id3V11Tag(KaitaiStream _io) {
            this(_io, null, null);
        }

        public Id3V11Tag(KaitaiStream _io, Id3v11 _parent) {
            this(_io, _parent, null);
        }

        public Id3V11Tag(KaitaiStream _io, Id3v11 _parent, Id3v11 _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.magic = this._io.readBytes(3);
            if (!(Arrays.equals(magic(), new byte[] { 84, 65, 71 }))) {
                throw new KaitaiStream.ValidationNotEqualError(new byte[] { 84, 65, 71 }, magic(), _io(), "/types/id3_v1_1_tag/seq/0");
            }
            this.title = this._io.readBytes(30);
            this.artist = this._io.readBytes(30);
            this.album = this._io.readBytes(30);
            this.year = new String(this._io.readBytes(4), Charset.forName("ASCII"));
            this.comment = this._io.readBytes(30);
            this.genre = GenreEnum.byId(this._io.readU1());
        }
        private byte[] magic;
        private byte[] title;
        private byte[] artist;
        private byte[] album;
        private String year;
        private byte[] comment;
        private GenreEnum genre;
        private Id3v11 _root;
        private Id3v11 _parent;
        public byte[] magic() { return magic; }

        /**
         * Song title
         */
        public byte[] title() { return title; }

        /**
         * Artist name
         */
        public byte[] artist() { return artist; }

        /**
         * Album title
         */
        public byte[] album() { return album; }

        /**
         * Year of release
         */
        public String year() { return year; }

        /**
         * Arbitary comment
         */
        public byte[] comment() { return comment; }
        public GenreEnum genre() { return genre; }
        public Id3v11 _root() { return _root; }
        public Id3v11 _parent() { return _parent; }
    }
    private Id3V11Tag id3v1Tag;
    public Id3V11Tag id3v1Tag() {
        if (this.id3v1Tag != null)
            return this.id3v1Tag;
        long _pos = this._io.pos();
        this._io.seek((_io().size() - 128));
        this.id3v1Tag = new Id3V11Tag(this._io, this, _root);
        this._io.seek(_pos);
        return this.id3v1Tag;
    }
    private Id3v11 _root;
    private KaitaiStruct _parent;
    public Id3v11 _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
}
