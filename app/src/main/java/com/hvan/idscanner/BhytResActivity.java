/* This file is part of ID Scanner.

ID Scanner is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

ID Scanner is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with ID Scanner.  If not, see <https://www.gnu.org/licenses/>. */

package com.hvan.idscanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class BhytResActivity extends AppCompatActivity {

    private static String bhyt_id = "";
    private static String name_hex = "";
    private static String birth_date = "";
    private static String addr_hex = "";
    private static String kcb = "";
    private static String cqbh = "";
    private static int sex;
    private static Date since = null;
    private static Date expire = null;
    private static Date issued = null;

    private static String hospitalQuery(String code) {
        Map<String, String> hospital_map = new HashMap<>();

        hospital_map.put("01 - 001", "Bệnh viện Hữu Nghị");
        hospital_map.put("01 - 002", "Phòng khám cán bộ BVĐK Xanh Pôn");
        hospital_map.put("01 - 003", "Bệnh viện đa khoa Xanh Pôn");
        hospital_map.put("01 - 004", "Bệnh viện đa khoa Đống Đa");
        hospital_map.put("01 - 005", "Bệnh viện Việt Nam - Cu Ba");
        hospital_map.put("01 - 006", "Bệnh viện Thanh Nhàn");
        hospital_map.put("01 - 007", "Bệnh viện E");
        hospital_map.put("01 - 008", "Ban Bảo vệ CSSK cán bộ TPHN (PK1)");
        hospital_map.put("01 - 009", "Bệnh viện Bưu điện (Bộ Bưu chính v.thông)");
        hospital_map.put("01 - 010", "Bệnh viện Giao thông vận tải trung ương");
        hospital_map.put("01 - 011", "Trung tâm Kỹ thuật chỉnh hình và Phục hồi CN");
        hospital_map.put("01 - 012", "Phòng khám đa khoa khu vực Đông Mỹ");
        hospital_map.put("01 - 013", "Bệnh viện Nông nghiệp");
        hospital_map.put("01 - 014", "Bệnh viện trung ương Quân đội 108");
        hospital_map.put("01 - 015", "Bệnh viện 354/TCHC");
        hospital_map.put("01 - 016", "Bệnh viện 103");
        hospital_map.put("01 - 017", "Phòng khám đa khoa khu vực Tô Hiệu");
        hospital_map.put("01 - 018", "Viện Y học hàng không");
        hospital_map.put("01 - 019", "Viện YH cổ truyền Quân đội");
        hospital_map.put("01 - 020", "Phòng khám 107 Tôn Đức Thắng (PK số 1-TTYT Đống Đa)");
        hospital_map.put("01 - 021", "Phòng khám đa khoa Kim Liên (PK số 3-TTYT Đống Đa)");
        hospital_map.put("01 - 022", "Phòng khám đa khoa 26 Lương Ngọc Quyến (TTYT Hoàn Kiếm)");
        hospital_map.put("01 - 023", "Phòng khám đa khoa 124 Hoàng Hoa Thám,  Tây Hồ");
        hospital_map.put("01 - 024", "Phòng khám đa khoa 103 Bà Triệu (TTYT Hai Bà Trưng)");
        hospital_map.put("01 - 025", "Bệnh viện đa khoa Đức Giang");
        hospital_map.put("01 - 026", "Phòng khám đa khoa Yên Viên (TTYT Gia Lâm)");
        hospital_map.put("01 - 027", "Phòng khám đa khoa Trâu Quỳ (TTYT Gia Lâm)");
        hospital_map.put("01 - 028", "Bệnh viện đa khoa YHCT Hà Nội");
        hospital_map.put("01 - 029", "Bệnh viện đa khoa Thanh Trì");
        hospital_map.put("01 - 030", "Phòng khám đa khoa Lĩnh Nam, Hoàng Mai");
        hospital_map.put("01 - 031", "Bệnh viện đa khoa Đông Anh");
        hospital_map.put("01 - 032", "Bệnh viện đa khoa Sóc Sơn");
        hospital_map.put("01 - 033", "Phòng khám đa khoa Trung Giã (TTYT Sóc Sơn)");
        hospital_map.put("01 - 034", "Phòng khám đa khoa Kim Anh (TTYT Sóc Sơn)");
        hospital_map.put("01 - 035", "Công ty CPKD & ĐTrị YT Đức Kiên (BV ĐKTN Hồng Hà)");
        hospital_map.put("01 - 036", "TYT Cơ quan Công ty cổ phần LILAMA 3 (YTCQ)");
        hospital_map.put("01 - 037", "Phòng khám đa khoa Ngọc Tảo (TTYT huyện Phúc Thọ)");
        hospital_map.put("01 - 038", "Phòng khám đa khoa Quang Minh");
        hospital_map.put("01 - 041", "Bệnh viện đa khoa tư nhân 16A Hà Đông (Công ty TNH");
        hospital_map.put("01 - 042", "Công ty TNHH khám chữa bệnh và tư vấn sức khỏe Ngọc Khánh");
        hospital_map.put("01 - 043", "Bệnh viện 198 (Bộ Công An)");
        hospital_map.put("01 - 044", "Trung tâm y tế MT lao động công thương");
        hospital_map.put("01 - 045", "Phòng khám đa khoa Linh Đàm, Hoàng Mai");
        hospital_map.put("01 - 046", "Bệnh viện chuyên khoa Nội Chữ Thập Xanh");
        hospital_map.put("01 - 047", "Bệnh viện YHCT TW");
        hospital_map.put("01 - 048", "Phòng khám đa khoa KV Dân Hòa");
        hospital_map.put("01 - 049", " Phòng khám đa khoa cơ sở 2 BV Nam Thăng Long");
        hospital_map.put("01 - 050", "Công ty TNHH1TV Thuốc lá Thăng Long (YTCQ)");
        hospital_map.put("01 - 051", "Bệnh viện Mắt Quốc tế Việt-Nga (C.ty CP Viện Mắt Quốc Tế Việt- Nga");
        hospital_map.put("01 - 052", "Bệnh viện TN CK khoa Mắt Việt-Nhật (C.ty CP tư vấn & đầu tư YT Việt-Nhật");
        hospital_map.put("01 - 053", "Bệnh viện tư nhân chuyên khoa Mắt HITEC");
        hospital_map.put("01 - 054", "Phòng khám đa khoa GTVT Gia Lâm");
        hospital_map.put("01 - 055", "Bệnh viện Xây dựng");
        hospital_map.put("01 - 056", "Bệnh viện đa khoa Hoè Nhai");
        hospital_map.put("01 - 057", "Phòng khám đa khoa 21 Phan Chu Trinh (TTYT Hoàn Kiếm");
        hospital_map.put("01 - 058", "Phòng khám 107 Trần Hưng Đạo");
        hospital_map.put("01 - 059", "Phòng khám đa khoa 50 Hàng Bún (TTYT Ba Đình)");
        hospital_map.put("01 - 060", "Bệnh viện Tuệ Tĩnh");
        hospital_map.put("01 - 061", "Cơ sở 2 Bệnh viện Đa khoa Nông nghiệp");
        hospital_map.put("01 - 062", "Bệnh viện YHCT Bộ Công an");
        hospital_map.put("01 - 063", "Bệnh viện Mắt Quốc tế-DND (C.ty TNHH Tư vấn và đầu tư QT)");
        hospital_map.put("01 - 064", "Phòng khám đa khoa số 2 (TTYTq. ĐĐ)");
        hospital_map.put("01 - 065", "Bệnh viện Dệt May");
        hospital_map.put("01 - 066", "Phòng khám 695 Lạc Long Quân, Tây Hồ");
        hospital_map.put("01 - 067", "Phòng khám đa khoa Sài Đồng (TTYT Long Biên)");
        hospital_map.put("01 - 068", "Bệnh viện YHCT Nam á");
        hospital_map.put("01 - 070", "Phòng khám đa khoa Mai Hương");
        hospital_map.put("01 - 071", "Bệnh viện Nam Thăng Long");
        hospital_map.put("01 - 072", "Trung tâm y tế Xây dựng Hà Nội");
        hospital_map.put("01 - 073", "Phòng khám đa khoa khu vực Nghĩa Tân, Cầu Giấy");
        hospital_map.put("01 - 074", "Phòng khám Trung tâm y tế quận Thanh Xuân");
        hospital_map.put("01 - 075", "Trung tâm y tế Công nghiệp Hà Nội");
        hospital_map.put("01 - 076", "Trung tâm Bác sĩ gia đình");
        hospital_map.put("01 - 077", "Bệnh viện Bắc Thăng Long");
        hospital_map.put("01 - 078", "Phòng khám đa khoa khu vực Yên Hoà, Cầu Giấy");
        hospital_map.put("01 - 079", "Phòng khám đa khoa khu vực Phú Lương, Hà Đông");
        hospital_map.put("01 - 080", "Bệnh viện Giao thông vận tải Vĩnh Phúc");
        hospital_map.put("01 - 081", "Trung tâm cấp cứu 115 Hà Nội (PK 11 Phan Chu Trinh)");
        hospital_map.put("01 - 082", "Bệnh viện đa khoa tư nhân Tràng An");
        hospital_map.put("01 - 083", "Phòng khám đa khoa Chèm");
        hospital_map.put("01 - 084", "Phòng khám đa khoa Miền Đông, Đông Anh");
        hospital_map.put("01 - 085", "Phòng khám bệnh đa khoa Khu vực 1, Đông Anh");
        hospital_map.put("01 - 086", "Trung tâm y tế Hàng Không");
        hospital_map.put("01 - 087", "Bệnh viện Than – Khoáng sản");
        hospital_map.put("01 - 088", "Phòng khám đa khoa Thạch Đà (TTYT huyện Mê Linh)");
        hospital_map.put("01 - 090", "Trung tâm y tế Giao thông 8");
        hospital_map.put("01 - 091", "Phòng khám đa khoa Cầu Diễn");
        hospital_map.put("01 - 092", " Phòng khám đa khoa trung tâm (TTYT Long Biên)");
        hospital_map.put("01 - 093", "Phòng khám đa khoa Đa Tốn (TTYT Gia Lâm)");
        hospital_map.put("01 - 094", "Bệnh viện đa khoa tư nhân Thiên Đức (C.ty TNHH MTV BV Thiên Đức");
        hospital_map.put("01 - 095", "Công ty cổ phần BVĐK Thăng Long");
        hospital_map.put("01 - 096", "Công ty CP Công nghệ y học Hồng Đức (Phòng khám đa khoa Việt Hàn)");
        hospital_map.put("01 - 097", "Bệnh viện Thể Thao Việt Nam");
        hospital_map.put("01 - 098", "Trung tâm y tế huyện Mê Linh");
        hospital_map.put("01 - 099", "Bệnh viện đa khoa huyện Mê Linh");
        hospital_map.put("01 - 100", "Phòng khám đa khoa khu vực Xuân Mai (TTYT Chương Mỹ)");
        hospital_map.put("01 - 101", "Công ty cổ phần Dệt 10-10 (YTCQ)");
        hospital_map.put("01 - 102", "VP Bộ Giáo dục và đào tạo (YTCQ)");
        hospital_map.put("01 - 103", "Cục phục vụ Ngoại giao đoàn (YTCQ)");
        hospital_map.put("01 - 104", "Công ty cổ phần Dệt Công nghiệp Hà Nội");
        hospital_map.put("01 - 105", "Phòng khám đa khoa 98 Hàng Buồm (thuộc C.ty CP Dược phẩm thiết bị y tế Hà Nội)");
        hospital_map.put("01 - 106", "Công ty TNHHNN 1TV Thoát nước Hà Nội (YTCQ)");
        hospital_map.put("01 - 107", "Phòng khám đa khoa Minh Phú");
        hospital_map.put("01 - 108", "Đại học Kinh tế quốc dân (YTCQ)");
        hospital_map.put("01 - 109", "Công ty TNHH S.A.S-CTAMAD (KS Melia) (YTCQ)");
        hospital_map.put("01 - 110", "Đại học Y Hà Nội (YTCQ)");
        hospital_map.put("01 - 111", "Trung tâm Tim mạch – Bệnh viện E");
        hospital_map.put("01 - 113", "Tổng Công ty Điện lực thành phố Hà Nội (YTCQ)");
        hospital_map.put("01 - 114", "Đại học Xây dựng (YTCQ)");
        hospital_map.put("01 - 115", "Công ty cổ phần Cồn Rượu Hà Nội (YTCQ)");
        hospital_map.put("01 - 116", "Trung tâm y tế Đại học Bách Khoa Hà Nội");
        hospital_map.put("01 - 117", "Bệnh viện Bạch Mai (YTCQ)");
        hospital_map.put("01 - 118", "Công ty TNHH 1TV Dệt kim Đông Xuân (YTCQ)");
        hospital_map.put("01 - 119", "Công ty cổ phần dụng cụ cơ khí xuất khẩu (YTCQ)");
        hospital_map.put("01 - 121", "Viễn thông Hà Nội (YTCQ)");
        hospital_map.put("01 - 122", "Công ty TNHHNN1TV Giầy Thượng Đình (YTCQ)");
        hospital_map.put("01 - 123", "Công ty TNHH LDKS Thống Nhất Metropole Hà Nội (YTCQ)");
        hospital_map.put("01 - 124", "Công ty kinh doanh nước sạch Hà Nội (YTCQ)");
        hospital_map.put("01 - 125", "Bệnh viện Tâm thần ban ngày Mai Hương");
        hospital_map.put("01 - 126", "Bệnh viện đa khoa tư nhân Hà Nội");
        hospital_map.put("01 - 127", "Công ty TNHHNN 1TV Môi trường đô thị (YTCQ)");
        hospital_map.put("01 - 128", "Trung tâm viễn thông khu vực 1(YTCQ)");
        hospital_map.put("01 - 130", "Đại học Sư phạm Hà Nội 2 (YTCQ)");
        hospital_map.put("01 - 131", "Văn phòng Đài truyền hình Việt Nam (YTCQ)");
        hospital_map.put("01 - 132", "Phòng khám đa khoa TN thuộc Công ty TNHH Y tế Tây Hà Thành");
        hospital_map.put("01 - 133", "Học viện Chính trị Hành Chính khu vực I (YTCQ)");
        hospital_map.put("01 - 135", "Phòng khám đa khoa khu vực Lương Mỹ (TTYT Chương Mỹ)");
        hospital_map.put("01 - 136", "Công ty Cổ phần sản xuất hàng thể thao (YTCQ)");
        hospital_map.put("01 - 137", "Văn phòng Trung ương Đảng (YTCQ)");
        hospital_map.put("01 - 138", "Văn phòng Viện KH&CN Việt Nam (YTCQ)");
        hospital_map.put("01 - 139", "Bệnh viện đa khoa Quốc tế Thu Cúc");
        hospital_map.put("01 - 140", "Công ty TNHHNN1TV chiếu sáng & TB đô thị (YTCQ)");
        hospital_map.put("01 - 141", "Bưu điện Thành phố Hà Nội (YTCQ)");
        hospital_map.put("01 - 143", "Trung tâm y tế Học viện Chính trị hành Chính QG HCM (YTCQ)");
        hospital_map.put("01 - 144", "Phòng y tế KS Intercontinental");
        hospital_map.put("01 - 146", "Phòng khám đa khoa Nam Hồng");
        hospital_map.put("01 - 147", "Học viện hành Chính- HVCTHCQGHCM (YTCQ)");
        hospital_map.put("01 - 148", "Đại học sư phạm Hà Nội (YTCQ)");
        hospital_map.put("01 - 149", "Công ty cổ phần xây lắp điện 1 (YTCQ)");
        hospital_map.put("01 - 150", "Bệnh viện Đông Đô");
        hospital_map.put("01 - 151", "Trạm y tế Công ty TNHH May Đức Giang (YTCQ)");
        hospital_map.put("01 - 152", "Trung tâm y tế Công ty cổ phần May 10");
        hospital_map.put("01 - 153", "Công ty TNHHNN 1TV Kim khí Thăng Long (YTCQ)");
        hospital_map.put("01 - 154", "Trung tâm y tế Hà Đông");
        hospital_map.put("01 - 155", "Phòng khám đa khoa  khu vực Đồng Tân, huyện ứng Hòa");
        hospital_map.put("01 - 156", "Phòng khám đa khoa khu vực Lưu Hoàng  (TTYT ứng Hoà)");
        hospital_map.put("01 - 157", "Trạm y tế Học viện Nông nghiệp Việt Nam");
        hospital_map.put("01 - 158", "Trung tâm y tế huyện Đan Phượng");
        hospital_map.put("01 - 159", "Đại học Mỏ Địa Chất (YTCQ)");
        hospital_map.put("01 - 160", "Bệnh viện đa khoa huyện Gia Lâm");
        hospital_map.put("01 - 161", "Bệnh viện đa khoa quốc tế Vinmec");
        hospital_map.put("01 - 162", "Công ty SXKD đầu tư & DV Việt Hà (YTCQ)");
        hospital_map.put("01 - 163", "Công ty cổ phần chế tạo máy điện VN-Hunggari (YTCQ)");
        hospital_map.put("01 - 164", "Công ty TNHHNN1TV xây lắp điện 4 (YTCQ)");
        hospital_map.put("01 - 165", "Công ty Cổ phần Kết cấu thép Xây dựng (YTCQ)");
        hospital_map.put("01 - 166", "Tổng công ty Thiết bị điện Đông Anh – Công ty cổ phần");
        hospital_map.put("01 - 169", "Phòng khám đa khoa khu vực An Mỹ  (TTYT Mỹ Đức)");
        hospital_map.put("01 - 171", "Phòng khám đa khoa khu vực Xuân Giang (TTYT huyện Sóc Sơn)");
        hospital_map.put("01 - 172", "Phòng khám đa khoa khu vực Hương Sơn  (TTYT Mỹ Đức)");
        hospital_map.put("01 - 173", "Đại học Luật Hà Nội (YTCQ)");
        hospital_map.put("01 - 174", "Bệnh viện Hữu Nghị (YTCQ)");
        hospital_map.put("01 - 175", "Học viện Tài Chính (YTCQ)");
        hospital_map.put("01 - 176", "Công ty TNHH hệ thống dây SUMI-HANEL (YTCQ");
        hospital_map.put("01 - 177", "Bệnh viện Phụ Sản An Thịnh");
        hospital_map.put("01 - 179", "Trung tâm Thực hành khám chữa bệnh Trường Cao đẳng y tế Hà Đông");
        hospital_map.put("01 - 183", "Phòng khám đa khoa Nguyễn Trọng Thọ");
        hospital_map.put("01 - 184", "Phòng khám đa khoa Thiện Nhân thuộc Công ty Cổ phần Du lịch và Dược phẩm Sơn Lâm");
        hospital_map.put("01 - 185", "Phòng khám đa khoa Quang Vinh");
        hospital_map.put("01 - 186", "Công ty LD Sakura Hà Nội Plaza");
        hospital_map.put("01 - 187", "Tổng Công ty cổ phần Thương mại Xây dựng (YTCQ)");
        hospital_map.put("01 - 188", "Bệnh viện tâm thần Mỹ Đức");
        hospital_map.put("01 - 189", "Công ty Cầu 14 (YTCQ)");
        hospital_map.put("01 - 191", "Bệnh viện đa khoa MEDLATEC");
        hospital_map.put("01 - 192", "XN Đầu máy Hà Nội (YTCQ)");
        hospital_map.put("01 - 193", "Bệnh viện Ung bướu Hưng Việt");
        hospital_map.put("01 - 194", "Tổng Công ty Hàng Hải Việt Nam (YTCQ)");
        hospital_map.put("01 - 195", "Phòng khám đa khoa Đại Thịnh");
        hospital_map.put("01 - 196", "Công ty quản lý Đường sắt Hà Thái (YTCQ)");
        hospital_map.put("01 - 198", "Trung tâm phòng chống Lao và bệnh Phổi Hà Đông");
        hospital_map.put("01 - 199", "Phòng khám đa khoa Ngãi Cầu");
        hospital_map.put("01 - 200", "Phòng khám đa khoa khu vực Tri Thủy (TTYT Phú Xuyên)");
        hospital_map.put("01 - 201", "Công ty cổ phần Dệt  Hà Đông Hanosimex");
        hospital_map.put("01 - 202", "Công ty CP Đầu tư và Xây dựng Xuân Mai");
        hospital_map.put("01 - 203", "Công ty TNHH Nhà máy Bia Châu Á - Thái Bình Dương");
        hospital_map.put("01 - 204", "Trường Đại học Lâm nghiệp (YTCQ)");
        hospital_map.put("01 - 205", "Trường CĐ Kinh tế kỹ thuật thương mại (YTCQ)");
        hospital_map.put("01 - 206", "Bệnh viện đa khoa tư nhân Trí Đức");
        hospital_map.put("01 - 208", "Phòng khám đa khoa thuộc CTCP Trung tâm bác sĩ gia đình Hà Nội");
        hospital_map.put("01 - 209", "Phòng khám đa khoa khu vực Minh Quang  (TTYT Ba Vì)");
        hospital_map.put("01 - 210", "Phòng khám đa khoa khu vực Bất Bạt  (TTY TBa Vì)");
        hospital_map.put("01 - 211", "Phòng khám đa khoa khu vực Tản Lĩnh  (TTYT Ba Vì)");
        hospital_map.put("01 - 212", "Phòng khám đa khoa khu vực Hòa Thạch");
        hospital_map.put("01 - 213", "Phòng khám đa khoa khu vực Yên Bình  (TTYT Thạch Thất)");
        hospital_map.put("01 - 214", "Công ty TNHH Medelab Việt Nam");
        hospital_map.put("01 - 215", "Bệnh viện Y học cổ truyền Trường Giang");
        hospital_map.put("01 - 216", "Bệnh viện CK Mắt Anh Sáng (C.ty TNHH đầu tư và TM QT Kim Thanh)");
        hospital_map.put("01 - 217", "Trung tâm y tế quận Bắc Từ Liêm");
        hospital_map.put("01 - 218", "Phòng khám đa khoa Bồ Đề");
        hospital_map.put("01 - 219", "Bệnh viện đa khoa Hồng Ngọc");
        hospital_map.put("01 - 223", "Trung tâm y tế quận Bắc Từ Liêm");
        hospital_map.put("01 - 224", "Phòng khám đa khoa Dr. Binh Teleclinic");
        hospital_map.put("01 - 225", "Phòng khám đa khoa Quảng Tây");
        hospital_map.put("01 - 227", "Phòng khám đa khoa Vietlife – MRI");
        hospital_map.put("01 - 228", "Cơ sở 2 Bệnh viện Da Liễu Hà Nội");
        hospital_map.put("01 - 229", "Trạm y tế phường Cổ Nhuế 2");
        hospital_map.put("01 - 230", "Trạm y tế phường Phúc Diễn");
        hospital_map.put("01 - 231", "Trạm y tế phường Phương Canh");
        hospital_map.put("01 - 232", "Trạm y tế phường Mỹ Đình 2");
        hospital_map.put("01 - 266", "Phòng khám đa khoa Yecxanh");
        hospital_map.put("01 - 301", "Đại học Dược Hà Nội (YTCQ)");
        hospital_map.put("01 - 311", "Đại học Công đoàn (YTCQ)");
        hospital_map.put("01 - 318", "Trường Cao đẳng Y tế Hà Nội (YTCQ)");
        hospital_map.put("01 - 334", "Đại học Giao thông vận tải (YTCQ)");
        hospital_map.put("01 - 336", "Trường Cao đẳng Du lịch Hà Nội (YTCQ)");
        hospital_map.put("01 - 355", "Đại học Kinh doanh và Công nghệ HN (YTCQ)");
        hospital_map.put("01 - 361", "Phòng khám đa khoa182 Lương Thế Vinh");
        hospital_map.put("01 - 365", "Đại học Công nghiệp Hà Nội (YTCQ)");
        hospital_map.put("01 - 367", "Đại học Lao động xã hội (YTCQ)");
        hospital_map.put("01 - 368", "Học viện Ngân hàng (YTCQ)");
        hospital_map.put("01 - 379", "Trung học tư thục KTKT Quang Trung (YTCQ)");
        hospital_map.put("01 - 388", "Học viện kỹ thuật Quân sự (YTCQ)");
        hospital_map.put("01 - 801", "Trung tâm y tế quận Ba Đình");
        hospital_map.put("01 - 802", "Trung tâm y tế quận Hoàn Kiếm");
        hospital_map.put("01 - 803", "Trung tâm y tế quận Tây Hồ");
        hospital_map.put("01 - 804", "Trung tâm y tế quận Long Biên");
        hospital_map.put("01 - 805", "Trung tâm y tế Cầu Giấy");
        hospital_map.put("01 - 806", "Trung tâm y tế quận Đống Đa");
        hospital_map.put("01 - 807", "Trung tâm y tế quận Hai Bà Trưng");
        hospital_map.put("01 - 808", "Trung tâm y tế quận Hoàng Mai");
        hospital_map.put("01 - 809", "Trung tâm y tế quận Thanh Xuân");
        hospital_map.put("01 - 810", "Trung tâm y tế huyện Sóc Sơn");
        hospital_map.put("01 - 811", "Trung tâm y tế huyện Đông Anh");
        hospital_map.put("01 - 812", "Trung tâm y tế huyện Gia Lâm");
        hospital_map.put("01 - 813", "Trung tâm y tế quận Nam Từ Liêm");
        hospital_map.put("01 - 814", "Trung tâm y tế huyện Thanh Trì");
        hospital_map.put("01 - 816", "Bệnh viện đa khoa Hà Đông");
        hospital_map.put("01 - 817", "Bệnh viện đa khoa Vân Đình");
        hospital_map.put("01 - 818", "Phòng khám cán bộ BVĐK Đống Đa");
        hospital_map.put("01 - 819", "Bệnh viện 105");
        hospital_map.put("01 - 820", "Bệnh viện đa khoa huyện Đan Phượng");
        hospital_map.put("01 - 821", "Bệnh viện đa khoa huyện Phú Xuyên");
        hospital_map.put("01 - 822", "Bệnh viện đa khoa huyện Ba Vì");
        hospital_map.put("01 - 823", "Bệnh viện đa khoa huyện Chương Mỹ");
        hospital_map.put("01 - 824", "Bệnh viện đa khoa huyện Hoài Đức");
        hospital_map.put("01 - 825", "Bệnh viện đa khoa huyện Mỹ Đức");
        hospital_map.put("01 - 826", "Bệnh viện đa khoa huyện Phúc Thọ");
        hospital_map.put("01 - 827", "Bệnh viện đa khoa huyện Quốc Oai");
        hospital_map.put("01 - 828", "Bệnh viện đa khoa huyện Thạch Thất");
        hospital_map.put("01 - 829", "Bệnh viện đa khoa huyện Thanh Oai");
        hospital_map.put("01 - 830", "Bệnh viện đa khoa huyện Thường Tín");
        hospital_map.put("01 - 831", "Bệnh viện đa khoa Sơn Tây");
        hospital_map.put("01 - 832", "Phòng khám đa khoa KV Trung tâm Hà Đông");
        hospital_map.put("01 - 833", "Trung tâm y tế huyện ứng Hoà");
        hospital_map.put("01 - 834", "Ban Bảo vệ CSSK cán bộ thành phố  HN (Phòng khám 2)");
        hospital_map.put("01 - 836", "Trung tâm y tế thị xã Sơn Tây");
        hospital_map.put("01 - 837", "Trung tâm y tế huyện Ba Vì");
        hospital_map.put("01 - 838", "Trung tâm y tế huyện Phúc Thọ");
        hospital_map.put("01 - 839", "Phòng khám đa khoa khu vực Liên Hồng");
        hospital_map.put("01 - 840", "Trung tâm y tế huyện Hoài Đức");
        hospital_map.put("01 - 841", "Trung tâm y tế huyện Quốc Oai");
        hospital_map.put("01 - 842", "Trung tâm y tế huyện Thạch Thất");
        hospital_map.put("01 - 843", "Trung tâm y tế huyện Chương Mỹ");
        hospital_map.put("01 - 844", "Trung tâm y tế huyện Thanh Oai");
        hospital_map.put("01 - 845", "Trung tâm y tế huyện Thường Tín");
        hospital_map.put("01 - 846", "Trung tâm y tế huyện Phú Xuyên");
        hospital_map.put("01 - 847", "Trung tâm y tế huyện Mỹ Đức");
        hospital_map.put("01 - 848", "Phòng khám cán bộ BVĐK Đức Giang");
        hospital_map.put("01 - 849", "Phòng khám cán bộ Bệnh viện Thanh Nhàn");
        hospital_map.put("01 - 850", "Phòng khám cán bộ BVĐK Sơn Tây");
        hospital_map.put("01 - 851", "Phòng khám cán bộ BVĐK Vân Đình");
        hospital_map.put("01 - 852", "Phòng khám cán bộ BVĐK Thanh Trì");
        hospital_map.put("01 - 853", "Phòng khám cán bộ BVĐK Sóc Sơn");
        hospital_map.put("01 - 854", "Phòng khám cán bộ BVĐK Đông Anh");
        hospital_map.put("01 - 855", "Phòng khám cán bộ Trung tâm y tế Từ Liêm");
        hospital_map.put("01 - 856", "Phòng khám cán bộ Trung tâm y tế huyện Gia Lâm");
        hospital_map.put("01 - 857", "Phòng khám cán bộ BVĐK huyện Mê Linh");
        hospital_map.put("01 - 858", "Phòng khám cán bộ BVĐK huyện Ba Vì");
        hospital_map.put("01 - 859", "Phòng khám cán bộ BVĐK Hà Đông");
        hospital_map.put("01 - 860", "Phòng khám cán bộ BVĐK huyện Thường Tín");
        hospital_map.put("01 - 861", "Phòng khám cán bộ BVĐK huyện Phú Xuyên");
        hospital_map.put("01 - 862", "Phòng khám cán bộ BVĐK huyện Đan Phượng");
        hospital_map.put("01 - 901", "Bệnh viện Hữu nghị Việt Đức");
        hospital_map.put("01 - 902", "Bệnh viện Da liễu Hà Nội");
        hospital_map.put("01 - 903", "Bệnh viện Phối Hà Nội");
        hospital_map.put("01 - 904", "Bệnh viện Phối Hà Nội");
        hospital_map.put("01 - 905", "Bệnh viện Phụ sản TW");
        hospital_map.put("01 - 906", "Bệnh viện K");
        hospital_map.put("01 - 907", "Bệnh viện Mắt trung ương");
        hospital_map.put("01 - 908", "Bệnh viện Răng Hàm Mặt Trung ương Hà Nội");
        hospital_map.put("01 - 909", "Bệnh viện Tai Mũi Họng TW");
        hospital_map.put("01 - 910", "Bệnh viện Phổi Trung ương");
        hospital_map.put("01 - 911", "Bệnh viện Mắt Hà Nội");
        hospital_map.put("01 - 912", "Bệnh viện Châm cứu Trung ương");
        hospital_map.put("01 - 913", "Viện sốt rét KST, CT TW");
        hospital_map.put("01 - 914", "Bệnh viện Nội tiết");
        hospital_map.put("01 - 915", "Bệnh viện Nhi Trung ương");
        hospital_map.put("01 - 916", "Bệnh viện Ung bướu Hà Nội");
        hospital_map.put("01 - 917", "Viện Bỏng Lê Hữu Trác");
        hospital_map.put("01 - 918", "Bệnh viện Thận Hà Nội");
        hospital_map.put("01 - 919", "Bệnh viện Tim Hà Nội");
        hospital_map.put("01 - 920", "Viện Huyết học và Truyền máu TW");
        hospital_map.put("01 - 921", "Công ty TNHH Bệnh viện Việt Pháp – Hà Nội");
        hospital_map.put("01 - 922", "Bệnh viện Mắt kỹ thuật cao Hà Nội – Chi nhánh Công ty TNHH Phát triển");
        hospital_map.put("01 - 923", "Bệnh viện Da liễu TW");
        hospital_map.put("01 - 924", "Bệnh viện bệnh Nhiệt đới Trung ương");
        hospital_map.put("01 - 925", "Bệnh viện Lão khoa TW");
        hospital_map.put("01 - 926", "Bệnh viện Mắt Sài Gòn Hà Nội (Chi nhánh Cty TNHH BV Mắt Thái Thành Nam)");
        hospital_map.put("01 - 927", "Viện Y học phóng xạ & U bướu Quân đội");
        hospital_map.put("01 - 929", "Bệnh viện Bạch Mai");
        hospital_map.put("01 - 930", "Bệnh viện Điều dưỡng và PHCN");
        hospital_map.put("01 - 931", "Bệnh viện Tâm Thần Hà Nội");
        hospital_map.put("01 - 933", "Công ty TNHH Bệnh viện Hồng Ngọc (BV Hồng Ngọc)");
        hospital_map.put("01 - 934", "Bệnh viện Đại học Y Hà Nội");
        hospital_map.put("01 - 935", "Bệnh viện YHCT Hà Đông");
        hospital_map.put("01 - 936", "Bệnh viện Mắt Hà Đông");
        hospital_map.put("01 - 937", "Trung tâm Thừa kế ứng dụng đông y");
        hospital_map.put("01 - 938", "Bệnh viện Tâm thần Trung ương");
        hospital_map.put("01 - 939", "Khoa khám bệnh cơ sở 2 – Bệnh viện nhiệt đới Trung ương");
        hospital_map.put("01 - 940", "Phòng khám đa khoa trực thuộc  công ty cổ phần y tế – Khám chữa bệnh Việt Nam");
        hospital_map.put("01 - 968", "Phòng khám đa khoa trực thuộc Công ty Cổ phần Trung Anh");
        hospital_map.put("01 - 941", "Phòng khám đa khoa Minh Ngọc trực thuộc Công ty Cổ phần phát triển kỹ thuật y học Minh Ngọc");
        hospital_map.put("01 - 267", "Bệnh viện đa khoa Bảo Sơn 2");

        String queryRes = hospital_map.get(code);
        if (queryRes == null) {
            return code;
        } else {
            return queryRes;
        }
    }

    private void errorMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BhytResActivity.this);
        builder.setMessage(getString(R.string.scan_error))
                .setTitle(getString(R.string.scan_error_title))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        BhytResActivity.this.finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private static String convHex(String hex) {
        byte[] data = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }

        String res;
        try {
            res = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            res = ex.toString();
        }

        return res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_res_bhyt);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Intent intent = getIntent();
        final String[] raw_data = intent.getStringExtra("scan_data").split(Pattern.quote("|"));
        if (raw_data.length != 15) {
            errorMessage();
        }

        try {
            // Raw information scanned from Health Insurance
            bhyt_id = raw_data[0];
            name_hex = raw_data[1];
            birth_date = raw_data[2];
            sex = Integer.valueOf(raw_data[3]);
            addr_hex = raw_data[4];
            kcb = raw_data[5];
            try {
                since = formatter.parse(raw_data[6]);
                issued = formatter.parse(raw_data[8]);
                if (raw_data[7].equals("-")) {
                    expire = formatter.parse(raw_data[12]);
                } else {
                    expire = formatter.parse(raw_data[7]);
                }
            } catch (ParseException ex) {
                since = issued = expire = null;
            }
            cqbh = raw_data[9];
            ////////////////////////////////////////////////
        } catch (ArrayIndexOutOfBoundsException ex) {
            errorMessage();
        }

        TextView tv_bhyt_id = findViewById(R.id.textView_bhyt_id);
        tv_bhyt_id.setText(bhyt_id);

        final TextView tv_bhyt_name = findViewById(R.id.textView_bhyt_name);
        final String name = convHex(name_hex);
        tv_bhyt_name.setText(name);
        tv_bhyt_name.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(tv_bhyt_name.getText().toString().equals(name_hex))) {
                    tv_bhyt_name.setText(name_hex);
                } else {
                    tv_bhyt_name.setText(name);
                }
            }
        });

        TextView tv_bhyt_ns = findViewById(R.id.textView_bhyt_ns);
        tv_bhyt_ns.setText(birth_date);

        final String addr = convHex(addr_hex);
        final TextView tv_bhyt_addr = findViewById(R.id.textView_bhyt_addr);
        tv_bhyt_addr.setText(addr);
        tv_bhyt_addr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(tv_bhyt_addr.getText().toString().equals(addr_hex))) {
                    tv_bhyt_addr.setText(addr_hex);
                } else {
                    tv_bhyt_addr.setText(addr);
                }
            }
        });

        TextView tv_bhyt_gt = findViewById(R.id.textView_bhyt_gt);
        if (sex == 1) {
            tv_bhyt_gt.setText(R.string.male);
        } else {
            tv_bhyt_gt.setText(R.string.female);
        }

        TextView tv_bhyt_hsd = findViewById(R.id.textView_bhyt_hsd);
        try {
            tv_bhyt_hsd.setText(String.format("%s - %s", formatter.format(since), formatter.format(expire)));
        } catch (NullPointerException ex) {
            errorMessage();
        }

        final TextView tv_bhyt_kcb = findViewById(R.id.textView_bhyt_dkkcbbd);
        final String kcb_name = hospitalQuery(kcb);
        tv_bhyt_kcb.setText(kcb_name);
        tv_bhyt_kcb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(tv_bhyt_kcb.getText().toString().equals(kcb_name))) {
                    tv_bhyt_kcb.setText(kcb_name);
                } else {
                    tv_bhyt_kcb.setText(kcb);
                }
            }
        });

        TextView tv_bhyt_cap = findViewById(R.id.textView_bhyt_cap);
        if (!(issued == null)) { tv_bhyt_cap.setText(formatter.format(issued)); }

        TextView tv_bhyt_cqbh = findViewById(R.id.textView_bhyt_cqbh);
        tv_bhyt_cqbh.setText(cqbh);
    }
}
